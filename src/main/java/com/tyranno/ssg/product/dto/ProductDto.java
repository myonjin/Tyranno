package com.tyranno.ssg.product.dto;

import com.tyranno.ssg.vendor.domain.Vendor;

public class ProductDto {
//    "productId":"Long",
//    "productName":"String",
//    "price":"int",
//    "productRate":"float",
//    "vendorName":"String",
//    "imageUrl":"String",
//    "discount":"int"
//    "reviewCount":"int",
//    "isLiked":"int"
    private Long productId;
    private String productName;
    private int price;
    private float productRate;
    private Vendor vendor;
    private String imageUrl;
    private int discount;
    private int reviewCount;
    private int isLiked;
}
