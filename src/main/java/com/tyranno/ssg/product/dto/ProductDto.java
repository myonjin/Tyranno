package com.tyranno.ssg.product.dto;

import com.tyranno.ssg.vendor.domain.Vendor;
import lombok.*;

@Data
@NoArgsConstructor
@Setter
public class ProductDto {
    private Long productId;
    private String productName;
    private int price;
    private float productRate;
    private String vendorName;
    private String imageUrl;
    private int discount;
    private int reviewCount;
    private byte isLiked;

//    @Builder
//    public ProductDto(Long productId, String productName, int price, float productRate, String vendorName,
//                      String imageUrl, int discount, int reviewCount, byte isLiked) {
//        this.productId = productId;
//        this.productName = productName;
//        this.price = price;
//        this.productRate = productRate;
//        this.vendorName = vendorName;
//        this.imageUrl = imageUrl;
//        this.discount = discount;
//        this.reviewCount = reviewCount;
//        this.isLiked = isLiked;
//    }
    @Builder
    public ProductDto(Long id, String productName, Integer productPrice, Float productRate, Integer reviewCount) {
    }
}