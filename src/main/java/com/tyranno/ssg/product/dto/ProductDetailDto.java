package com.tyranno.ssg.product.dto;

import com.tyranno.ssg.vendor.domain.Vendor;
import com.tyranno.ssg.vendor.dto.VendorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDto {
    private String productName;
    private int price;
    private float productRate;
    private String detailContent;

    // 다른 곳
    private List<VendorDto> vendor;
    private List<String> imageUrl;
    private int discount;
    private int reviewCount;
}
