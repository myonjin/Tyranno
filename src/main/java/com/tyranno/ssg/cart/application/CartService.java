package com.tyranno.ssg.cart.application;

import com.tyranno.ssg.cart.dto.Request.CartAddDto;
import com.tyranno.ssg.cart.dto.Request.CartCountModifyDto;
import com.tyranno.ssg.cart.dto.Response.CartListDto;

import java.util.List;

public interface CartService {
    void addCart(CartAddDto cartAddDto, String uuid);
    List<CartListDto> getCartList(String uuid);
    void deleteCartList(List<Long> cartDeleteList);
    void deleteCart(Long cartId);
    void modifyItemCount(CartCountModifyDto cartCountModifyDto);
}
