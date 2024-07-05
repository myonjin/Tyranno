package com.tyranno.ssg.option.infrastructure;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.option.domain.QOption;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionRepositoryImpl extends QuerydslRepositorySupport {
    private final QOption qOption = QOption.option;
    private final JPAQueryFactory jpaQueryFactory;

    public OptionRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Option.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Option> getOptionProduct(Long productId, Long colorId, Long sizeId, Long extraId, Long etcId) {
        return jpaQueryFactory.select(qOption)
                .from(qOption)
                .where(eqProduct(productId), eqColor(colorId), eqSize(sizeId), eqExtra(extraId), eqEtc(etcId))
                .fetch();
    }

    private BooleanExpression eqProduct(Long productId) {
        if (productId == null || productId == 0) {
            return null;
        }
        return qOption.product.id.eq(productId);
    }

    private BooleanExpression eqColor(Long colorId) {
        if (colorId == null || colorId == 0) {
            return null;
        }
        return qOption.color.id.eq(colorId);
    }

    private BooleanExpression eqSize(Long sizeId) {
        if (sizeId == null || sizeId == 0) {
            return null;
        }
        return qOption.size.id.eq(sizeId);
    }

    private BooleanExpression eqExtra(Long extraId) {
        if (extraId == null || extraId == 0) {
            return null;
        }
        return qOption.extra.id.eq(extraId);
    }

    private BooleanExpression eqEtc(Long etcId) {
        if (etcId == null || etcId == 0) {
            return null;
        }
        return qOption.etc.id.eq(etcId);
    }


}
