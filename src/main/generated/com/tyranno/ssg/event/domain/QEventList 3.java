package com.tyranno.ssg.event.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventList is a Querydsl query type for EventList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventList extends EntityPathBase<EventList> {

    private static final long serialVersionUID = -436243255L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventList eventList = new QEventList("eventList");

    public final QEvent event;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.tyranno.ssg.product.domain.QProduct product;

    public QEventList(String variable) {
        this(EventList.class, forVariable(variable), INITS);
    }

    public QEventList(Path<? extends EventList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventList(PathMetadata metadata, PathInits inits) {
        this(EventList.class, metadata, inits);
    }

    public QEventList(Class<? extends EventList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new QEvent(forProperty("event")) : null;
        this.product = inits.isInitialized("product") ? new com.tyranno.ssg.product.domain.QProduct(forProperty("product")) : null;
    }

}

