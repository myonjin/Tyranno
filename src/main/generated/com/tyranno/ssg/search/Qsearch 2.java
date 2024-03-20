package com.tyranno.ssg.search;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * Qsearch is a Querydsl query type for search
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class Qsearch extends EntityPathBase<search> {

    private static final long serialVersionUID = 459569767L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final Qsearch search = new Qsearch("search");

    public final com.tyranno.ssg.global.QGlobalCreateTime _super = new com.tyranno.ssg.global.QGlobalCreateTime(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath searchWord = createString("searchWord");

    public final com.tyranno.ssg.users.domain.QUsers users;

    public Qsearch(String variable) {
        this(search.class, forVariable(variable), INITS);
    }

    public Qsearch(Path<? extends search> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public Qsearch(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public Qsearch(PathMetadata metadata, PathInits inits) {
        this(search.class, metadata, inits);
    }

    public Qsearch(Class<? extends search> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new com.tyranno.ssg.users.domain.QUsers(forProperty("users")) : null;
    }

}

