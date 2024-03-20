package com.tyranno.ssg.option.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOption is a Querydsl query type for Option
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOption extends EntityPathBase<Option> {

    private static final long serialVersionUID = -633159827L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOption option = new QOption("option");

    public final QColor color;

    public final QEtc etc;

    public final QExtra extra;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSize size;

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public QOption(String variable) {
        this(Option.class, forVariable(variable), INITS);
    }

    public QOption(Path<? extends Option> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOption(PathMetadata metadata, PathInits inits) {
        this(Option.class, metadata, inits);
    }

    public QOption(Class<? extends Option> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.color = inits.isInitialized("color") ? new QColor(forProperty("color")) : null;
        this.etc = inits.isInitialized("etc") ? new QEtc(forProperty("etc")) : null;
        this.extra = inits.isInitialized("extra") ? new QExtra(forProperty("extra")) : null;
        this.size = inits.isInitialized("size") ? new QSize(forProperty("size")) : null;
    }

}

