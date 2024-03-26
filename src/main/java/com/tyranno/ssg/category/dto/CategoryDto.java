package com.tyranno.ssg.category.dto;

import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.dto.ProductDto;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
public class CategoryDto {
    private ProductDto product;
    private Long detailId;
    private Long smallId;
    private Long middleId;
    private Long largeId;

    @Builder
    public CategoryDto(ProductDto product, Long detailId, Long smallId, Long middleId, Long largeId) {
        this.product = product;
        this.detailId = detailId;
        this.smallId = smallId;
        this.middleId = middleId;
        this.largeId = largeId;
    }
}
