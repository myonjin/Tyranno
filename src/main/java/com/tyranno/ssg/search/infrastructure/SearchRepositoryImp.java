package com.tyranno.ssg.search.infrastructure;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tyranno.ssg.category.domain.QCategory;
import com.tyranno.ssg.category.domain.QCategoryDetail;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.domain.QProduct;
import com.tyranno.ssg.vendor.domain.QVendorProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class SearchRepositoryImp extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public SearchRepositoryImp(JPAQueryFactory jpaQueryFactory) {
        super(Product.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Long> searchProductIdsByKeywordAndFilter(String searchKeyword, Integer sortCriterion, Integer page, Integer maxPrice, Integer minPrice, Float minRate) {
        QProduct product = QProduct.product;
        QVendorProduct vendorProduct = QVendorProduct.vendorProduct;
        QCategoryDetail categoryDetail = QCategoryDetail.categoryDetail;
        QCategory category = QCategory.category;

        // productName으로 제품을 찾아 productId 반환
        List<Long> productIdsFromProductName = jpaQueryFactory
                .select(product.id)
                .from(product)
                .where(product.productName.containsIgnoreCase(searchKeyword))
                .fetch();

        // vendorName으로 벤더를 찾아 vendorId 반환
        List<Long> vendorIds = jpaQueryFactory
                .select(vendorProduct.vendor.id)
                .from(vendorProduct)
                .where(vendorProduct.vendor.vendorName.containsIgnoreCase(searchKeyword))
                .fetch();
        // categoryName으로 카테고리를 찾아 categoryId 반환
        List<Long> categoryDetailIds = jpaQueryFactory
                .select(categoryDetail.id)
                .from(categoryDetail)
                .where(categoryDetail.detailName.containsIgnoreCase(searchKeyword))
                .fetch();

        // vendorId를 이용하여 해당하는 제품의 productId 반환
        List<Long> productIdsFromVendor = jpaQueryFactory
                .select(vendorProduct.product.id)
                .from(vendorProduct)
                .where(vendorProduct.vendor.id.in(vendorIds))
                .fetch();

        // vendorId를 이용하여 해당하는 제품의 productId 반환
        List<Long> productIdsFromCategory = jpaQueryFactory
                .select(category.product.id)
                .from(category)
                .where(category.detailId.in(categoryDetailIds))
                .fetch();

        // 중복을 제거한 productId 리스트 생성
        Set<Long> uniqueProductIds = new HashSet<>(productIdsFromProductName);
        uniqueProductIds.addAll(productIdsFromVendor);
        uniqueProductIds.addAll(productIdsFromCategory);

        // 중복 제거 후의 productId 리스트를 사용하여 쿼리 수행
        com.querydsl.jpa.impl.JPAQuery<Long> query = jpaQueryFactory
                .select(product.id)
                .from(product)
                .where(product.id.in(uniqueProductIds));

        // 가격, 평점 조건 추가
        BooleanExpression priceExpression;
        if (minPrice != null && maxPrice != null) {
            priceExpression = product.productPrice.between(minPrice, maxPrice);
        } else if (minPrice != null) {
            priceExpression = product.productPrice.goe(minPrice);
        } else if (maxPrice != null) {
            priceExpression = product.productPrice.loe(maxPrice);
        } else {
            // minPrice와 maxPrice가 모두 null인 경우, 가격 제한 없음
            priceExpression = null;
        }

        BooleanExpression rateExpression = minRate != null ? product.productRate.goe(minRate) : null;

        // 가격과 평점 조건을 함께 적용
        if (priceExpression != null && rateExpression != null) {
            query.where(priceExpression.and(rateExpression));
        } else if (priceExpression != null) {
            query.where(priceExpression);
        } else if (rateExpression != null) {
            query.where(rateExpression);
        }

        // 정렬 기준에 따른 OrderSpecifier 생성
        OrderSpecifier<?> orderSpecifier = createProductSpecifier(sortCriterion);
        query.orderBy(orderSpecifier); // 정렬 기준 적용

        // 페이지네이션 적용
        if (page != null && page > 0) {
            int offset = (page - 1) * 10;
            query.offset(offset).limit(10);
        } else {
            query.limit(10);
        }

        return query.fetch();
    }

    private OrderSpecifier<?> createProductSpecifier(Integer sortCriterion) {
        QProduct product = QProduct.product;
        return switch (sortCriterion) {
            case 1 -> new OrderSpecifier<>(Order.ASC, product.productPrice);
            case 2 -> new OrderSpecifier<>(Order.DESC, product.productPrice);
            case 3 -> new OrderSpecifier<>(Order.DESC, product.productRate);
            case 4 -> new OrderSpecifier<>(Order.DESC, product.reviewCount);
            default -> new OrderSpecifier<>(Order.ASC, product.id);
        };
    }
}
