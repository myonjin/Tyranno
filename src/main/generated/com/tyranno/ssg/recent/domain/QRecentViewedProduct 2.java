package com.tyranno.ssg.recent.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecentViewedProduct is a Querydsl query type for RecentViewedProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecentViewedProduct extends EntityPathBase<RecentViewedProduct> {

    private static final long serialVersionUID = -1187341806L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecentViewedProduct recentViewedProduct = new QRecentViewedProduct("recentViewedProduct");

    public final com.tyranno.ssg.global.QGlobalCreateTime _super = new com.tyranno.ssg.global.QGlobalCreateTime(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> isView = createNumber("isView", Byte.class);

    public final com.tyranno.ssg.product.domain.QProduct product;

    public final com.tyranno.ssg.users.domain.QUsers users;

    public QRecentViewedProduct(String variable) {
        this(RecentViewedProduct.class, forVariable(variable), INITS);
    }

    public QRecentViewedProduct(Path<? extends RecentViewedProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecentViewedProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecentViewedProduct(PathMetadata metadata, PathInits inits) {
        this(RecentViewedProduct.class, metadata, inits);
    }

    public QRecentViewedProduct(Class<? extends RecentViewedProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.tyranno.ssg.product.domain.QProduct(forProperty("product")) : null;
        this.users = inits.isInitialized("users") ? new com.tyranno.ssg.users.domain.QUsers(forProperty("users")) : null;
    }

}

