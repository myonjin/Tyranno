package com.tyranno.ssg.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Getter
@NoArgsConstructor
public class ReviewIdListDto {
    private List<Map<String, Object>> reviewIds;

    @Builder
    public ReviewIdListDto(List<Map<String, Object>> reviewIds) {
        this.reviewIds = reviewIds;
    }

}
