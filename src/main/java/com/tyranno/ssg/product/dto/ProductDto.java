package com.tyranno.ssg.product.dto;

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
    private byte isLiked;

    public ProductDto(Long productId, String productName, int price, float productRate, Vendor vendor, String imageUrl, int discount, int reviewCount, byte isLiked) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.productRate = productRate;
        this.vendor = vendor;
        this.imageUrl = imageUrl;
        this.discount = discount;
        this.reviewCount = reviewCount;
        this.isLiked = isLiked;
    }

    public ProductDto() {
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProductRate(float productRate) {
        this.productRate = productRate;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setIsLiked(byte isLiked) {
        this.isLiked = isLiked;
    }
}