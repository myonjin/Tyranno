package com.tyranno.ssg.cart.dto.Request;

import com.tyranno.ssg.cart.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartCountModifyDto {
    private Long cartId;
    private int count;

    public Cart toEntity(Cart cart) {
        return Cart.builder()
                .id(cartId)
                .users(cart.getUsers())
                .option(cart.getOption())
                .count(count)
                .isKeep(cart.getIsKeep())
                .build();
    }
}
