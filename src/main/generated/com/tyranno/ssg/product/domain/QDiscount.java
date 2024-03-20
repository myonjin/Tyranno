package com.tyranno.ssg.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDiscount is a Querydsl query type for Discount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDiscount extends EntityPathBase<Discount> {

    private static final long serialVersionUID = 2110385861L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDiscount discount1 = new QDiscount("discount1");

    public final NumberPath<Integer> discount = createNumber("discount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> discountEnd = createDateTime("discountEnd", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> discountStart = createDateTime("discountStart", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProduct product;

    public QDiscount(String variable) {
        this(Discount.class, forVariable(variable), INITS);
    }

    public QDiscount(Path<? extends Discount> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDiscount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDiscount(PathMetadata metadata, PathInits inits) {
        this(Discount.class, metadata, inits);
    }

    public QDiscount(Class<? extends Discount> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
    }

}

