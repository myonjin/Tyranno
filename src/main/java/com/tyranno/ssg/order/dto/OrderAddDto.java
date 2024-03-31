package com.tyranno.ssg.order.dto;

import com.tyranno.ssg.global.GlobalCreateTime;
import com.tyranno.ssg.order.domain.OrderList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddDto {

    private long optionId;
    private String deliveryRequest;
    private String deliveryBase;
    private String deliveryDetail;
    private int zipCode;
    private String receiverName;
    private String receiverPhoneNumber;
    private String orderName;
    private String orderNumber;
    private String orderEmail;
    private int count;
    private int money;
    private int totalMoney;
    private int discount;

    public OrderList toEntity(String uuid, OrderAddDto orderAddDto, String createdOrderNumber) {
        return OrderList.builder()
                .uuid(uuid)
                .deliveryRequest(orderAddDto.getDeliveryRequest())
                .deliveryBase(orderAddDto.getDeliveryBase())
                .deliveryDetail(orderAddDto.getDeliveryDetail())
                .zipCode(orderAddDto.getZipCode())
                .receiverName(orderAddDto.getReceiverName())
                .receiverPhoneNumber(orderAddDto.getReceiverPhoneNumber())
                .orderName(orderAddDto.getOrderName())
                .orderEmail(orderAddDto.getOrderEmail())
                .orderNumber(createdOrderNumber)
                .totalMoney(orderAddDto.getTotalMoney())
                .isOrderConfirm((byte) 0)
                .build();
    }
}
//
//"receiverName" : "string",
//        "deliveryBase": "string",
//        "deliveryDetail": "string",
//        "zipCode" : "int"
//        "receiverPhoneNumber" : "string",
//        "deliveryRequest":"string",
//        "howToReceive":"string:,
//        "orderName" : "string"
//        "orderPhoneNumber":
//        "orderEmail": "string"
//        "optionId":"long",
//        "count":"int",
//        "money":"int"
//        "totalMoney":"int",
//        "discount":'int"