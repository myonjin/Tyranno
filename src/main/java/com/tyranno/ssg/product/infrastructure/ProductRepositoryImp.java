package com.tyranno.ssg.product.infrastructure;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tyranno.ssg.category.domain.QCategory;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.domain.QDiscount;
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

    public List<Long> getProductIdList(Long largeId, Long middleId,
                                       Long smallId, Long detailId, String sortCriterion, @Nullable Integer paging) {
        OrderSpecifier<?> orderSpecifier = createOrderSpecifier(sortCriterion);
        QCategory category = QCategory.category;
        QDiscount discount = QDiscount.discount1;
        return jpaQueryFactory.select(category.product.id)
                .from(category)
                .where(
                        gtBoardId(paging),
                        largeIdEq(category, largeId),
                        middleIdEq(category, middleId),
                        smallIdEq(category, smallId),
                        detailIdEq(category, detailId))
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

    private BooleanExpression gtBoardId(@Nullable Integer paging) {
        QCategory category = QCategory.category;
        return paging == null ? null : category.product.id.gt(paging);
    }

    private OrderSpecifier<?> createOrderSpecifier(String sortCriterion) {
        QCategory category = QCategory.category;
        return switch (sortCriterion) {
            case "1" -> new OrderSpecifier<>(Order.ASC, category.product.productPrice);
            case "2" -> new OrderSpecifier<>(Order.DESC, category.product.productPrice);
            case "3" -> new OrderSpecifier<>(Order.DESC, category.product.productRate);
            case "4" -> new OrderSpecifier<>(Order.DESC, category.product.reviewCount);
            default -> new OrderSpecifier<>(Order.ASC, category.product.id);
        };
    }
}
