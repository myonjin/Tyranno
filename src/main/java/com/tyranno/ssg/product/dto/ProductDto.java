package com.tyranno.ssg.product.dto;

import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.vendor.domain.Vendor;

public class ProductDto {
    private Long productId;
    private String productName;
    private int price;
    private float productRate;
    private Vendor vendor;
    private String imageUrl;
    private int discount;
    private int reviewCount;
    private int isLiked;


    public ProductDto(Long productId, String productName, int price, float productRate, Vendor vendor, String imageUrl, int discount, int reviewCount, boolean isLiked) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.productRate = productRate;
//        this.Vendor = vendor;
        this.imageUrl = imageUrl;
        this.discount = discount;
        this.reviewCount = reviewCount;
//        this.isLiked = isLiked;
    }

    public ProductDto() {

    }

    public void setProductId(Long id) {
    }

    public void setProductName(String productName) {
    }

    public void setPrice(Integer productPrice) {
    }

    public void setProductRate(Float productRate) {
    }

    public void setReviewCount(Integer reviewCount) {
    }

    public void setVendorName(String vendorName) {
    }

    public void setImageUrl(String imageUrl) {
    }
}
