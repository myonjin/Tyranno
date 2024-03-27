package com.tyranno.ssg.option.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExtra is a Querydsl query type for Extra
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExtra extends EntityPathBase<Extra> {

    private static final long serialVersionUID = -999252456L;

    public static final QExtra extra = new QExtra("extra");

    public final StringPath extraName = createString("extraName");

    public final NumberPath<Integer> extraPrice = createNumber("extraPrice", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QExtra(String variable) {
        super(Extra.class, forVariable(variable));
    }

    public QExtra(Path<? extends Extra> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExtra(PathMetadata metadata) {
        super(Extra.class, metadata);
    }

}

