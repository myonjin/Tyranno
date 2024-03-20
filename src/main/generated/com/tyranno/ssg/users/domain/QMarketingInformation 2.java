package com.tyranno.ssg.users.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMarketingInformation is a Querydsl query type for MarketingInformation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarketingInformation extends EntityPathBase<MarketingInformation> {

    private static final long serialVersionUID = 1656778371L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMarketingInformation marketingInformation = new QMarketingInformation("marketingInformation");

    public final com.tyranno.ssg.global.QGlobalTime _super = new com.tyranno.ssg.global.QGlobalTime(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> isAgree = createNumber("isAgree", Byte.class);

    public final QMarketing marketing;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUsers users;

    public QMarketingInformation(String variable) {
        this(MarketingInformation.class, forVariable(variable), INITS);
    }

    public QMarketingInformation(Path<? extends MarketingInformation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMarketingInformation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMarketingInformation(PathMetadata metadata, PathInits inits) {
        this(MarketingInformation.class, metadata, inits);
    }

    public QMarketingInformation(Class<? extends MarketingInformation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.marketing = inits.isInitialized("marketing") ? new QMarketing(forProperty("marketing")) : null;
        this.users = inits.isInitialized("users") ? new QUsers(forProperty("users")) : null;
    }

}

