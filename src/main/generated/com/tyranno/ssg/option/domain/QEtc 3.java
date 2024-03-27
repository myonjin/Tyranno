package com.tyranno.ssg.option.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEtc is a Querydsl query type for Etc
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEtc extends EntityPathBase<Etc> {

    private static final long serialVersionUID = -336235108L;

    public static final QEtc etc = new QEtc("etc");

    public final StringPath additionalOption = createString("additionalOption");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QEtc(String variable) {
        super(Etc.class, forVariable(variable));
    }

    public QEtc(Path<? extends Etc> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEtc(PathMetadata metadata) {
        super(Etc.class, metadata);
    }

}

