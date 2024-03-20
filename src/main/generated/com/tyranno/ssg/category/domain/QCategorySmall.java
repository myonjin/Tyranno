package com.tyranno.ssg.category.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategorySmall is a Querydsl query type for CategorySmall
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategorySmall extends EntityPathBase<CategorySmall> {

    private static final long serialVersionUID = -376477304L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategorySmall categorySmall = new QCategorySmall("categorySmall");

    public final QCategoryMiddle categoryMiddle;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath smallName = createString("smallName");

    public QCategorySmall(String variable) {
        this(CategorySmall.class, forVariable(variable), INITS);
    }

    public QCategorySmall(Path<? extends CategorySmall> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategorySmall(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategorySmall(PathMetadata metadata, PathInits inits) {
        this(CategorySmall.class, metadata, inits);
    }

    public QCategorySmall(Class<? extends CategorySmall> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categoryMiddle = inits.isInitialized("categoryMiddle") ? new QCategoryMiddle(forProperty("categoryMiddle"), inits.get("categoryMiddle")) : null;
    }

}

