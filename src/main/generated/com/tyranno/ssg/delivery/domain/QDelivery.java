package com.tyranno.ssg.delivery.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDelivery is a Querydsl query type for Delivery
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDelivery extends EntityPathBase<Delivery> {

    private static final long serialVersionUID = -1008259925L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDelivery delivery = new QDelivery("delivery");

    public final StringPath deliveryBase = createString("deliveryBase");

    public final StringPath deliveryDetail = createString("deliveryDetail");

    public final StringPath deliveryName = createString("deliveryName");

    public final StringPath homeNumber = createString("homeNumber");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> isBaseDelivery = createNumber("isBaseDelivery", Byte.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath receiverName = createString("receiverName");

    public final com.tyranno.ssg.users.domain.QUsers users;

    public final NumberPath<Integer> zipCode = createNumber("zipCode", Integer.class);

    public QDelivery(String variable) {
        this(Delivery.class, forVariable(variable), INITS);
    }

    public QDelivery(Path<? extends Delivery> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDelivery(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDelivery(PathMetadata metadata, PathInits inits) {
        this(Delivery.class, metadata, inits);
    }

    public QDelivery(Class<? extends Delivery> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new com.tyranno.ssg.users.domain.QUsers(forProperty("users")) : null;
    }

}

