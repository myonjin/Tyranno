package com.tyranno.ssg.users.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMarketing is a Querydsl query type for Marketing
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarketing extends EntityPathBase<Marketing> {

    private static final long serialVersionUID = 1629745897L;

    public static final QMarketing marketing = new QMarketing("marketing");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath type = createString("type");

    public QMarketing(String variable) {
        super(Marketing.class, forVariable(variable));
    }

    public QMarketing(Path<? extends Marketing> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMarketing(PathMetadata metadata) {
        super(Marketing.class, metadata);
    }

}

