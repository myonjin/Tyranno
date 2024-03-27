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
public class ColorStockDto {
    @NotNull
    private Long colorId;
    @NotNull
    private String color;
    @NotNull
    private Integer stock;

    public static ColorStockDto fromEntity(Long colorId, String color, Integer stock) {
        return ColorStockDto.builder()
                .colorId(colorId)
                .color(color)
                .stock(stock)
                .build();
    }

}
