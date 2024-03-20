package com.tyranno.ssg.vendor.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVendorProduct is a Querydsl query type for VendorProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVendorProduct extends EntityPathBase<VendorProduct> {

    private static final long serialVersionUID = 588871068L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVendorProduct vendorProduct = new QVendorProduct("vendorProduct");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.tyranno.ssg.product.domain.QProduct product;

    public final QVendor vendor;

    public QVendorProduct(String variable) {
        this(VendorProduct.class, forVariable(variable), INITS);
    }

    public QVendorProduct(Path<? extends VendorProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVendorProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVendorProduct(PathMetadata metadata, PathInits inits) {
        this(VendorProduct.class, metadata, inits);
    }

    public QVendorProduct(Class<? extends VendorProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.tyranno.ssg.product.domain.QProduct(forProperty("product")) : null;
        this.vendor = inits.isInitialized("vendor") ? new QVendor(forProperty("vendor")) : null;
    }

}

