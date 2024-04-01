package com.tyranno.ssg.cart.application;

import com.tyranno.ssg.cart.dto.Request.CartAddDto;
import com.tyranno.ssg.cart.dto.Request.CartCountModifyDto;
import com.tyranno.ssg.cart.dto.Response.CartListDto;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.users.domain.Users;

import java.util.List;

public interface CartService {
    String addCartByOption(CartAddDto cartAddDto, String uuid);
    String addCartByProduct(Long productId, String uuid);
    String addCart(String uuid, Option option, int count);
    List<CartListDto> getCartList(String uuid);
    void deleteCartList(List<Long> cartDeleteList);
    void deleteCart(Long cartId);
    void modifyItemCount(CartCountModifyDto cartCountModifyDto);
}
