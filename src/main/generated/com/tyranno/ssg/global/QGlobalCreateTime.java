package com.tyranno.ssg.global;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGlobalCreateTime is a Querydsl query type for GlobalCreateTime
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QGlobalCreateTime extends EntityPathBase<GlobalCreateTime> {

    private static final long serialVersionUID = 580642672L;

    public static final QGlobalCreateTime globalCreateTime = new QGlobalCreateTime("globalCreateTime");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public QGlobalCreateTime(String variable) {
        super(GlobalCreateTime.class, forVariable(variable));
    }

    public QGlobalCreateTime(Path<? extends GlobalCreateTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGlobalCreateTime(PathMetadata metadata) {
        super(GlobalCreateTime.class, metadata);
    }

}

