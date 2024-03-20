package com.tyranno.ssg.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductThum is a Querydsl query type for ProductThum
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductThum extends EntityPathBase<ProductThum> {

    private static final long serialVersionUID = -181180009L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductThum productThum = new QProductThum("productThum");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageName = createString("imageName");

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Byte> priority = createNumber("priority", Byte.class);

    public final QProduct product;

    public QProductThum(String variable) {
        this(ProductThum.class, forVariable(variable), INITS);
    }

    public QProductThum(Path<? extends ProductThum> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductThum(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductThum(PathMetadata metadata, PathInits inits) {
        this(ProductThum.class, metadata, inits);
    }

    public QProductThum(Class<? extends ProductThum> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
    }

}

