package com.tyranno.ssg.global;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGlobalTime is a Querydsl query type for GlobalTime
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QGlobalTime extends EntityPathBase<GlobalTime> {

    private static final long serialVersionUID = 1699750292L;

    public static final QGlobalTime globalTime = new QGlobalTime("globalTime");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QGlobalTime(String variable) {
        super(GlobalTime.class, forVariable(variable));
    }

    public QGlobalTime(Path<? extends GlobalTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGlobalTime(PathMetadata metadata) {
        super(GlobalTime.class, metadata);
    }

}

