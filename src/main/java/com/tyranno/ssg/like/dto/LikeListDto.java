package com.tyranno.ssg.like.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Getter
@NoArgsConstructor
public class LikeListDto {
    private List<Map<String, Object>> likeIds;

    @Builder
    public LikeListDto(List<Map<String, Object>> likeIds) {
        this.likeIds = likeIds;
    }
}

