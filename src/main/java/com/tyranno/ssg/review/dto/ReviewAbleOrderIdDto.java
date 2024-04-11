package com.tyranno.ssg.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class ReviewAbleOrderIdDto {
    private List<Map<String, Object>> reviewAbleOrderIds;

    @Builder
    public ReviewAbleOrderIdDto(List<Map<String, Object>> reviewAbleOrderIds) {
        this.reviewAbleOrderIds = reviewAbleOrderIds;
    }
}
