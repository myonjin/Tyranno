package com.tyranno.ssg.product.infrastructure;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tyranno.ssg.category.domain.QCategory;
import com.tyranno.ssg.product.domain.Product;
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
                                                 Long smallId, Long detailId, Integer sortCriterion, Integer page) {
        OrderSpecifier<?> orderSpecifier = createOrderSpecifier(sortCriterion);
        QCategory category = QCategory.category;

        com.querydsl.jpa.impl.JPAQuery<Long> query = jpaQueryFactory
                .select(category.product.id)
                .from(category)
                .where(
                        largeIdEq(category, largeId),
                        middleIdEq(category, middleId),
                        smallIdEq(category, smallId),
                        detailIdEq(category, detailId))
                .orderBy(orderSpecifier);

        if (page != null && page > 0) {
            // page가 제공된 경우, 해당 인덱스 이후의 상품을 가져오도록 offset 설정
            int offset = (page - 1) * 10; // 10개씩 끊어서 가져오므로 10을 곱해야함
            query.offset(offset).limit(10);
        } else {
            // page가 null이거나 0 이하인 경우, 처음부터 10개의 상품을 가져오도록 설정
            query.limit(10);
        }

        return query.fetch();
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
    private OrderSpecifier<?> createOrderSpecifier(Integer sortCriterion) {
        QCategory category = QCategory.category;
        return switch (sortCriterion) {
            case 1 -> new OrderSpecifier<>(Order.ASC, category.product.productPrice);
            case 2 -> new OrderSpecifier<>(Order.DESC, category.product.productPrice);
            case 3 -> new OrderSpecifier<>(Order.DESC, category.product.productRate.divide(category.product.reviewCount));
            case 4 -> new OrderSpecifier<>(Order.DESC, category.product.reviewCount);
            default -> new OrderSpecifier<>(Order.ASC, category.product.id);
        };
    }
    public List<Long> searchAllProductIdsByCategory(Long largeId, Long middleId,
                                                 Long smallId, Long detailId, Integer sortCriterion) {
        OrderSpecifier<?> orderSpecifier = createOrderSpecifier(sortCriterion);
        QCategory category = QCategory.category;

        com.querydsl.jpa.impl.JPAQuery<Long> query = jpaQueryFactory
                .select(category.product.id)
                .from(category)
                .where(
                        largeIdEq(category, largeId),
                        middleIdEq(category, middleId),
                        smallIdEq(category, smallId),
                        detailIdEq(category, detailId))
                .orderBy(orderSpecifier);
        return query.fetch();
    }
}
