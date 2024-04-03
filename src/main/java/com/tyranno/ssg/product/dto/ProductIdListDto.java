package com.tyranno.ssg.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductIdListDto {
    private List<Long> productIds;

    @Builder
    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
