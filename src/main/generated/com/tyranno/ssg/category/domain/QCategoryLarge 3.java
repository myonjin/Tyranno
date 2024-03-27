package com.tyranno.ssg.category.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryLarge is a Querydsl query type for CategoryLarge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryLarge extends EntityPathBase<CategoryLarge> {

    private static final long serialVersionUID = -383283268L;

    public static final QCategoryLarge categoryLarge = new QCategoryLarge("categoryLarge");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath largeName = createString("largeName");

    public final StringPath largeUrl = createString("largeUrl");

    public QCategoryLarge(String variable) {
        super(CategoryLarge.class, forVariable(variable));
    }

    public QCategoryLarge(Path<? extends CategoryLarge> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryLarge(PathMetadata metadata) {
        super(CategoryLarge.class, metadata);
    }

}

