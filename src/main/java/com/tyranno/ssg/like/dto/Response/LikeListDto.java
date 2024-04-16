package com.tyranno.ssg.like.dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Getter
@NoArgsConstructor
public class LikeListDto {
    private List<Map<String, Object>> productIds;

    @Builder
    public LikeListDto(List<Map<String, Object>> productIds) {
        this.productIds = productIds;
    }
}

