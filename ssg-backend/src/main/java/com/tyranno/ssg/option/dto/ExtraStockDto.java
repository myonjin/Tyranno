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
public class ExtraStockDto {
    @NotNull
    private Long extraId;
    @NotNull
    private String ExtraName;
    @NotNull
    private Integer extraPrice;
    @NotNull
    private Integer stock;
}
