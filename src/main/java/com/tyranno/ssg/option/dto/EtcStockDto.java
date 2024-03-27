package com.tyranno.ssg.option.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class EtcStockDto {
    @NotNull
    private Long etcId;
    @NotNull
    private String additionalOption;
    @NotNull
    private Integer stock;
}
