package com.tyranno.ssg.category.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategoryMiddle is a Querydsl query type for CategoryMiddle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryMiddle extends EntityPathBase<CategoryMiddle> {

    private static final long serialVersionUID = 1038718260L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategoryMiddle categoryMiddle = new QCategoryMiddle("categoryMiddle");

    public final QCategoryLarge categoryLarge;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath middleName = createString("middleName");

    public QCategoryMiddle(String variable) {
        this(CategoryMiddle.class, forVariable(variable), INITS);
    }

    public QCategoryMiddle(Path<? extends CategoryMiddle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategoryMiddle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategoryMiddle(PathMetadata metadata, PathInits inits) {
        this(CategoryMiddle.class, metadata, inits);
    }

    public QCategoryMiddle(Class<? extends CategoryMiddle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categoryLarge = inits.isInitialized("categoryLarge") ? new QCategoryLarge(forProperty("categoryLarge")) : null;
    }

}

