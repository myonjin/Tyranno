package com.tyranno.ssg.product.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ProductIdListDto {
    private List<Map<String, Object>> productIds;
    private int totalCount;
    private int lastPage;
    private int currentPage;

    @Builder
    public ProductIdListDto(List<Map<String, Object>> productIds, int totalCount, int lastPage, int currentPage) {
        this.productIds = productIds;
        this.totalCount = totalCount;
        this.lastPage = lastPage;
        this.currentPage = currentPage;
    }
}
