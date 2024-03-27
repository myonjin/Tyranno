package com.tyranno.ssg.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailCategoryDto {
    private Long detailId;
    private String detailName;
}
