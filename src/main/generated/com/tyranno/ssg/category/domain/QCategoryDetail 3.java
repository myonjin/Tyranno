package com.tyranno.ssg.category.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategoryDetail is a Querydsl query type for CategoryDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryDetail extends EntityPathBase<CategoryDetail> {

    private static final long serialVersionUID = 777835504L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategoryDetail categoryDetail = new QCategoryDetail("categoryDetail");

    public final QCategorySmall categorySmall;

    public final StringPath detailName = createString("detailName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCategoryDetail(String variable) {
        this(CategoryDetail.class, forVariable(variable), INITS);
    }

    public QCategoryDetail(Path<? extends CategoryDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategoryDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategoryDetail(PathMetadata metadata, PathInits inits) {
        this(CategoryDetail.class, metadata, inits);
    }

    public QCategoryDetail(Class<? extends CategoryDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categorySmall = inits.isInitialized("categorySmall") ? new QCategorySmall(forProperty("categorySmall"), inits.get("categorySmall")) : null;
    }

}

