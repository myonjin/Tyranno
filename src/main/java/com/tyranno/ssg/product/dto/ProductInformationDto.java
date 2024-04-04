package com.tyranno.ssg.product.dto;

import com.tyranno.ssg.like.domain.Like;
import com.tyranno.ssg.product.domain.Discount;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.domain.ProductThum;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.dto.UsersInfoDto;
import com.tyranno.ssg.vendor.domain.Vendor;
import lombok.*;

@Getter
@NoArgsConstructor
public class ProductInformationDto {
    private Long productId;
    private String productName;
    private int price;
    private float productRate;
    private int reviewCount;
    private String vendorName;
    private String imageUrl;
    private int discount;
    private byte isLiked;

    @Builder
    public ProductInformationDto(Long productId, String productName, int price, float productRate, int reviewCount,
                                 String vendorName, String imageUrl, int discount, byte isLiked) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.productRate = productRate;
        this.reviewCount = reviewCount;
        this.vendorName = vendorName;
        this.imageUrl = imageUrl;
        this.discount = discount;
        this.isLiked = isLiked;
    }
}