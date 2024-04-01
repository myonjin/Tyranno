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
    private Long optionId;
    private int count;
}
