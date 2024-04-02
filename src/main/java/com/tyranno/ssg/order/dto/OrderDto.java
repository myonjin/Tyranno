package com.tyranno.ssg.order.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long orderId; //주문 아이디
    private Long productId; //상품 아이디
    private Long optionId; //옵션 아이디

    private int count; //주문 수량
    private int money; //주문 금액

    private String productName; //상품 이름
    private int price; //상
    private String vendorName;
    private String imageUrl;
    private int discount;



}
