package com.tyranno.ssg.category.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class SmallCategoryDto {
    private Long smallId;
    private String smallName;

    @Builder
    public SmallCategoryDto(Long smallId, String smallName) {
        this.smallId = smallId;
        this.smallName = smallName;
    }
}
