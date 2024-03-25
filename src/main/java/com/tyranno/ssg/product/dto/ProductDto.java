package com.tyranno.ssg.product.dto;

import com.tyranno.ssg.vendor.domain.Vendor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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


}