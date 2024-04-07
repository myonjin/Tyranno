package com.tyranno.ssg.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNonOrderDto {
    private String OrderName; //주문자 이름
    private String OrderPhoneNumber; //주문자 휴대폰 번호
    private String OrderNumber; //주문번호

    // orderName string orderPhoneNumber string OrderNumber string
}
