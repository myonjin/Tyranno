package com.tyranno.ssg.cart.application;

import com.tyranno.ssg.cart.dto.Request.*;
import com.tyranno.ssg.cart.dto.Response.CartListDto;
import com.tyranno.ssg.option.domain.Option;

import java.util.List;

public interface CartService {
    String addCartByOption(CartAddDto cartAddDto, String uuid);
    String addCartByProduct(Long productId, String uuid);
    String addCart(String uuid, Option option, int count);
    List<CartListDto> getCartList(String uuid);
    void deleteCartList(List<CartIdDto> cartDeleteList);
    void deleteCart(Long cartId);
    void modifyCount(CartCountModifyDto cartCountModifyDto);

    void modifyOption(CartOptionModifyDto cartOptionModifyDto);

    int getUsersCartCount(String uuid);

    void modifyCartIsKeep(CartKeepModifyDto cartKeepModifyDto);
}
