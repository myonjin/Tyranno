package com.tyranno.ssg.category.dto;

import com.tyranno.ssg.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long productId;
//    private Product product;
    private Long detailId;
    private Long smallId;
    private Long middleId;
    private Long largeId;
}
