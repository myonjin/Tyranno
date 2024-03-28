package com.tyranno.ssg.cart.dto.Response;

import com.tyranno.ssg.option.domain.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionInfoDto {
    private String color;
    private String size;
    private String additional_option;
    private String extra_name;
    private int extra_price;

    public static OptionInfoDto FromOption(Option option) {
        return OptionInfoDto.builder()
                .color(option.getColor().getColor())
                .size(option.getSize().getSize())
                .additional_option(option.getEtc().getAdditional_option())
                .extra_name(option.getExtra().getExtraName())
                .extra_price(option.getExtra().getExtraPrice())
                .build();
    }
}
