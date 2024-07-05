package com.tyranno.ssg.option.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SizeStockDto {
    @NotNull
    private Long sizeId;
    @NotNull
    private String size;
    @NotNull
    private Integer stock;
}
