package com.tyranno.ssg.option.dto;

import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionNamesDto {
    private String color;
    private String size;
    private String additional_option;
    private String extra_name;
    private int total_price;

    public static OptionNamesDto FromEntity(Option option, Product product) {
        return OptionNamesDto.builder()
                .color(option.getColor() != null ? option.getColor().getColor() : null)
                .size(option.getSize() != null ? option.getSize().getSize() : null)
                .additional_option(option.getEtc() != null ? option.getEtc().getAdditionalOption() : null)
                .extra_name(option.getExtra() != null ? option.getExtra().getExtraName() : null)
                .total_price(option.getExtra() != null ? option.getExtra().getExtraPrice() + product.getProductPrice() : product.getProductPrice())
                .build();
    }
}
