package com.tyranno.ssg.address.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = -736720821L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddress address = new QAddress("address");

    public final StringPath addressBase = createString("addressBase");

    public final StringPath addressDetail = createString("addressDetail");

    public final StringPath addressName = createString("addressName");

    public final StringPath homeNumber = createString("homeNumber");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> isMain = createNumber("isMain", Byte.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath receiverName = createString("receiverName");

    public final com.tyranno.ssg.users.domain.QUsers users;

    public final NumberPath<Integer> zipCode = createNumber("zipCode", Integer.class);

    public QAddress(String variable) {
        this(Address.class, forVariable(variable), INITS);
    }

    public QAddress(Path<? extends Address> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAddress(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAddress(PathMetadata metadata, PathInits inits) {
        this(Address.class, metadata, inits);
    }

    public QAddress(Class<? extends Address> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new com.tyranno.ssg.users.domain.QUsers(forProperty("users")) : null;
    }

}

