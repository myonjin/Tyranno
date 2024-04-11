package com.tyranno.ssg.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class ProductIdListDto {
    private List<Map<String, Object>> productIds;

    @Builder
    public void setProductIds(List<Map<String, Object>> productIds) {
        this.productIds = productIds;
    }
}
