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
    private Long optionId;
    private Long productId;
    private String productName;
    private String imageUrl;
    private int totalPrice;
    private int discount;
    private int count;
    private int isKeep;
    private String vendorName;

    public static CartListDto FromEntity(Cart cart, String imageUrl, int discount, String vendorName) {
        return CartListDto.builder()
                .cartId(cart.getId())
                .optionId(cart.getOption().getId())
                .productId(cart.getOption().getProduct().getId())
                .productName(cart.getOption().getProduct().getProductName())
                .imageUrl(imageUrl)
                .totalPrice(cart.getOption().getProduct().getProductPrice() + (
                        cart.getOption().getExtra() != null ? cart.getOption().getExtra().getExtraPrice() : 0))
                .discount(discount) // 할인율 적용금액도 주기 //비회원은?
                .count(cart.getCount())
                .isKeep(cart.getIsKeep())
                .vendorName(vendorName)
                .build();
    }//밴더 끼리 묵ㄲ기
}
