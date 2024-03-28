package com.tyranno.ssg.cart.dto.Response;

import com.tyranno.ssg.cart.domain.Cart;
import com.tyranno.ssg.option.domain.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartListDto {
    private Long cartId;
    private Long productId;
    private Long optionId;
    private String imageUrl; //
    private int productPrice;
    private int discount; //
    private int count;
    private int isKeep;
    private String vendorName; //

    public static CartListDto FromEntity(Cart cart, String imageUrl, int discount, String vendorName) {
        return CartListDto.builder()
                .cartId(cart.getId())
                .productId(cart.getOption().getProduct().getId())
                .optionId(cart.getOption().getId())
                .imageUrl(imageUrl)
                .discount(discount)
                .count(cart.getCount())
                .isKeep(cart.getIsKeep())
                .vendorName(vendorName)
                .build();
    }
}
