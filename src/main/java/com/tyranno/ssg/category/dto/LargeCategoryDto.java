package com.tyranno.ssg.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LargeCategoryDto {
    private Long largeId;
    private String largeName;
    private String largeImageUrl;
}
