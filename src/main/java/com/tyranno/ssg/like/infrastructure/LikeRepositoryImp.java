package com.tyranno.ssg.like.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tyranno.ssg.like.domain.Like;
import com.tyranno.ssg.like.domain.QLike;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikeRepositoryImp extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public LikeRepositoryImp(JPAQueryFactory jpaQueryFactory) {
        super(Like.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Long> searchProductIdsByUsersId(Long id, Integer page) {
        QLike like = QLike.like;
        com.querydsl.jpa.impl.JPAQuery<Long> query = jpaQueryFactory
                .select(like.product.id)
                .from(like)
                .where(like.users.id.eq(id));
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
}
