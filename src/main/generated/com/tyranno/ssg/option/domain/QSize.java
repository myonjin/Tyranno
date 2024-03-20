package com.tyranno.ssg.option.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSize is a Querydsl query type for Size
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSize extends EntityPathBase<Size> {

    private static final long serialVersionUID = -1832946439L;

    public static final QSize size1 = new QSize("size1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath size = createString("size");

    public QSize(String variable) {
        super(Size.class, forVariable(variable));
    }

    public QSize(Path<? extends Size> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSize(PathMetadata metadata) {
        super(Size.class, metadata);
    }

}

