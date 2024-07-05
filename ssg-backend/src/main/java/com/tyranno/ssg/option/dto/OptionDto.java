package com.tyranno.ssg.option.dto;

import com.tyranno.ssg.option.domain.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionDto {
    @NotNull
    private Long optionId;
    @NotNull
    private String productName;
    @NotNull
    private Integer productPrice;

    private int discount;

    private Color color;

    private Size size;

    private Extra extra;

    private Etc etc;
    @NotNull
    private Integer stock;

    public static OptionDto fromEntity(Option option, int discount) {
        return OptionDto.builder()
                .optionId(option.getId())
                .productName(option.getProduct().getProductName())
                .productPrice(option.getProduct().getProductPrice())
                .discount(discount)
                .color(option.getColor())
                .size(option.getSize())
                .extra(option.getExtra())
                .etc(option.getEtc())
                .stock(option.getStock())
                .build();
    }
}
//    public static OptionDto fromEntity(Option option) {
//        return OptionDto.builder()
//                .optionId(option.getId())
//                .productName(option.getProduct().getProductName())
//                .productPrice(option.getProduct().getProductPrice())
//                .color(option.getColor() != null ? option.getColor().getColor() : null)
//                .size(option.getSize() != null ? option.getSize().getSize() : null)
//                .extra(option.getExtra() != null ? option.getExtra().getExtraName() : null)
//                .etc(option.getExtra() != null ? option.getEtc().getAdditionalOption() : null)
//                .stock(option.getStock())
//                .build();
//    }