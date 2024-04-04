package com.tyranno.ssg.cart.dto.Request;

import com.tyranno.ssg.cart.domain.Cart;
import com.tyranno.ssg.option.domain.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartOptionModifyDto {
    private Long cartId;
    private Long optionId;

    public Cart toEntity(Cart cart, Option option){
        return Cart.builder()
                .id(cartId)
                .users(cart.getUsers())
                .option(option)
                .isKeep(cart.getIsKeep())
                .count(cart.getCount())
                .build();
    }
}
