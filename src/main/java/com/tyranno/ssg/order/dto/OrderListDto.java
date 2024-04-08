package com.tyranno.ssg.order.dto;


import com.tyranno.ssg.order.domain.OrderList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderListDto {
    private Long orderListId; //주문 리스트 아이디

    private int totalMoney; //총 결제 금액
    private String orderNumber; //주문 번호
    private LocalDateTime orderDate; //주문 일자

    private String receiverName; //받는사람 이름

    private List<OrderDto> orderDtoList; //주문 상품 각각 항목

    private byte isOrderConfirm; //주문 확정
    private byte orderStatus; //주문 상태

//    public static OrderListDto fromEntity(OrderList orderList, List<OrderDto> orderDtoList) {

}
