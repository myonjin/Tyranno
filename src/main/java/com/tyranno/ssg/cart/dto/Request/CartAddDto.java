package com.tyranno.ssg.cart.dto.Request;

import com.tyranno.ssg.cart.domain.Cart;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartAddDto {
    @NotNull
    private Long optionId;
    @NotNull
    private int count;
    @NotNull
    private Byte isKeep;

    public Cart toEntity(Users users, Option option) {
        return Cart.builder()
                .users(users)
                .option(option)
                .count(count)
                .isKeep(isKeep)
                .build();
    }
}
