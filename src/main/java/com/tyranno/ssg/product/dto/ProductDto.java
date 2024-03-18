package com.tyranno.ssg.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productName;
    private int price;
    private float productRate;
    private String detailContent;

    private List<String> vendor;
    private List<String> imageUrl;
    private int discount;
    private int reviewCount;
}
