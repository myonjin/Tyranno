package com.tyranno.ssg.review.infrastructure;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.review.domain.QReview;
import com.tyranno.ssg.review.domain.Review;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImp extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public ReviewRepositoryImp(JPAQueryFactory jpaQueryFactory) {
        super(Review.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Long> searchReviewIdsByProductId(Long productId, Integer sortCriterion, @Nullable Integer page) {
        OrderSpecifier<?> orderSpecifier = createOrderSpecifier(sortCriterion);
        QReview review = QReview.review;
        com.querydsl.jpa.impl.JPAQuery<Long> query = jpaQueryFactory
                .select(review.id)
                .from(review)
                .where(review.product.id.eq(productId))
                .orderBy(orderSpecifier);

        if (page != null && page > 0) {
            // page가 제공된 경우, 해당 인덱스 이후의 리뷰을 가져오도록 offset 설정
            int offset = (page - 1) * 10; // 10개씩 끊어서 가져오므로 20을 곱해야 함
            query.offset(offset).limit(10);
        } else {
            // page가 null이거나 0 이하인 경우, 처음부터 10개의 리뷰를 가져오도록 설정
            query.limit(10);
        }

        return query.fetch();
    }
    public List<Long> searchReviewIdsByUsersId(Long id, Integer sortCriterion, Integer page) {
        OrderSpecifier<?> orderSpecifier = createOrderSpecifier(sortCriterion);
        QReview review = QReview.review;
        com.querydsl.jpa.impl.JPAQuery<Long> query = jpaQueryFactory
                .select(review.id)
                .from(review)
                .where(review.users.id.eq(id))
                .orderBy(orderSpecifier);

        if (page != null && page > 0) {
            // page가 제공된 경우, 해당 인덱스 이후의 리뷰을 가져오도록 offset 설정
            int offset = (page - 1) * 10; // 10개씩 끊어서 가져오므로 10을 곱해야 함
            query.offset(offset).limit(10);
        } else {
            // page가 null이거나 0 이하인 경우, 처음부터 10개의 리뷰를 가져오도록 설정
            query.limit(10);
        }
        return query.fetch();
    }

    private OrderSpecifier<?> createOrderSpecifier(Integer sortCriterion) {
        QReview review = QReview.review;
        return switch (sortCriterion) {
            case 1 -> new OrderSpecifier<>(Order.DESC, review.rate);
            case 2 -> new OrderSpecifier<>(Order.ASC, review.rate);
            case 3 -> new OrderSpecifier<>(Order.DESC, review.createdAt);
            case 4 -> new OrderSpecifier<>(Order.ASC, review.createdAt);
            default -> new OrderSpecifier<>(Order.DESC, review.createdAt);
        };
    }


}
