package com.tyranno.ssg.product.infrastructure;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tyranno.ssg.category.domain.QCategory;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.domain.QProduct;
import com.tyranno.ssg.vendor.domain.QVendor;
import com.tyranno.ssg.vendor.domain.QVendorProduct;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImp extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public ProductRepositoryImp(JPAQueryFactory jpaQueryFactory) {
        super(Product.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Long> searchProductIdsByCategory(Long largeId, Long middleId,
                                       Long smallId, Long detailId, Integer sortCriterion, @Nullable Integer lastIndex) {
        OrderSpecifier<?> orderSpecifier = createOrderSpecifier(sortCriterion);
        QCategory category = QCategory.category;
        return jpaQueryFactory.select(category.product.id)
                .from(category)
                .where(
                        gtBoardId(lastIndex),
                        largeIdEq(category, largeId),
                        middleIdEq(category, middleId),
                        smallIdEq(category, smallId),
                        detailIdEq(category, detailId))
                .orderBy(orderSpecifier)
                .limit(20)
                .fetch();
    }

    public List<Long> searchProductIdsByKeyword(String searchKeyword, Integer sortCriterion, Integer lastIndex) {
        OrderSpecifier<?> orderSpecifier = createProductSpecifier(sortCriterion);
        QProduct product = QProduct.product;
        QVendor vendor = QVendor.vendor;
        QVendorProduct vendorProduct = QVendorProduct.vendorProduct;

        // Vendor에서 vendorId
        List<Long> vendorIds = jpaQueryFactory.select(vendor.id)
                .from(vendor)
                .where(vendor.vendorName.likeIgnoreCase("%" + searchKeyword + "%"))
                .fetch();

        // VendorProduct에서 상품 ID
        BooleanExpression vendorExpression = vendorIds.isEmpty() ? null : vendorProduct.vendor.id.in(vendorIds);

        // 검색 키워드
        BooleanExpression searchExpression = searchKeyword != null ?
                product.productName.likeIgnoreCase("%" + searchKeyword + "%") : null;

        // 일반 상품
        BooleanExpression generalProductExpression = searchKeyword != null ?
                searchExpression.and(product.notIn(
                        JPAExpressions.select(vendorProduct.product)
                                .from(vendorProduct)
                                .innerJoin(vendorProduct.vendor, vendor)
                                .where(vendor.id.in(vendorIds))
                )) : null;

        BooleanExpression finalExpression = null;
        if (vendorExpression != null && searchExpression != null) {
            finalExpression = vendorExpression.and(searchExpression);
        } else if (vendorExpression != null) {
            finalExpression = vendorExpression;
        } else if (searchExpression != null) {
            finalExpression = searchExpression;
        }
        return jpaQueryFactory.select(vendorProduct.id)
                .from(vendorProduct)
                .where(
                        gtProductBoardId(lastIndex),
                        finalExpression.or(generalProductExpression))
                .orderBy(orderSpecifier)
                .limit(20)
                .fetch();
    }

    private BooleanExpression largeIdEq(QCategory category, Long largeId) {
        if (largeId == null) {
            return null;
        }
        return category.largeId.eq(largeId);
    }

    private BooleanExpression middleIdEq(QCategory category, Long middleId) {
        if (middleId == null) {
            return null;
        }
        return category.middleId.eq(middleId);
    }

    private BooleanExpression smallIdEq(QCategory category, Long smallId) {
        if (smallId == null) {
            return null;
        }
        return category.smallId.eq(smallId);
    }

    private BooleanExpression detailIdEq(QCategory category, Long detailId) {
        if (detailId == null) {
            return null;
        }
        return category.detailId.eq(detailId);
    }

    private BooleanExpression gtBoardId(@Nullable Integer lastIndex) {
        QCategory category = QCategory.category;
        return lastIndex == null ? null : category.product.id.gt(lastIndex);
    }
    private BooleanExpression gtProductBoardId(@Nullable Integer lastIndex) {
        QProduct product = QProduct.product;
        return lastIndex == null ? null : product.id.gt(lastIndex);
    }

    private OrderSpecifier<?> createOrderSpecifier(Integer sortCriterion) {
        QCategory category = QCategory.category;
        return switch (sortCriterion) {
            case 1 -> new OrderSpecifier<>(Order.ASC, category.product.productPrice);
            case 2 -> new OrderSpecifier<>(Order.DESC, category.product.productPrice);
            case 3 -> new OrderSpecifier<>(Order.DESC, category.product.productRate);
            case 4 -> new OrderSpecifier<>(Order.DESC, category.product.reviewCount);
            default -> new OrderSpecifier<>(Order.ASC, category.product.id);
        };
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
