package com.tyranno.ssg.review.infrastructure;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tyranno.ssg.category.domain.QCategory;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.review.domain.QReview;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImp extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public ReviewRepositoryImp(JPAQueryFactory jpaQueryFactory) {
        super(Product.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Long> searchReviewIdsByProductId(Long productId, Integer sortCriterion, @Nullable Integer lastIndex) {
        OrderSpecifier<?> orderSpecifier = createOrderSpecifier(sortCriterion);
        QReview review = QReview.review;
        return jpaQueryFactory.select(review.id)
                .from(review)
                .where(
                        review.product.id.eq(productId),
                        gtBoardId(lastIndex)
                )
                .orderBy(orderSpecifier)
                .limit(20)
                .fetch();
    }

    private BooleanExpression gtBoardId(@Nullable Integer lastIndex) {
        QReview review = QReview.review;
        return lastIndex == null ? null : review.id.gt(lastIndex);
    }
    private OrderSpecifier<?> createOrderSpecifier(Integer sortCriterion) {
        QReview review = QReview.review;
        return switch (sortCriterion) {
            case 1 -> new OrderSpecifier<>(Order.DESC, review.rate);
            case 2 -> new OrderSpecifier<>(Order.ASC, review.rate);
            default -> new OrderSpecifier<>(Order.DESC, review.createdAt);
        };
    }

}
