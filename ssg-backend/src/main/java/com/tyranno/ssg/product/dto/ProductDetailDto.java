package com.tyranno.ssg.product.dto;

import com.tyranno.ssg.vendor.domain.Vendor;
import com.tyranno.ssg.vendor.dto.VendorDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailDto {
    private String productName;
    private int price;
    private float productRate;
    private String detailContent;

    // 다른 곳
    private VendorDto vendor;
    private List<String> imageUrl;
    private int discount;
    private int reviewCount;

    @Builder

    public ProductDetailDto(String productName, int price, float productRate, String detailContent, VendorDto vendor, List<String> imageUrl, int discount, int reviewCount) {
        this.productName = productName;
        this.price = price;
        this.productRate = productRate;
        this.detailContent = detailContent;
        this.vendor = vendor;
        this.imageUrl = imageUrl;
        this.discount = discount;
        this.reviewCount = reviewCount;
    }
}
