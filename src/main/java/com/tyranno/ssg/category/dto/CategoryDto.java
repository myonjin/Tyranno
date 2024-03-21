package com.tyranno.ssg.category.dto;

import com.tyranno.ssg.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Product product;
    private Long detailId;
    private Long smallId;
    private Long middleId;
    private Long largeId;
}
