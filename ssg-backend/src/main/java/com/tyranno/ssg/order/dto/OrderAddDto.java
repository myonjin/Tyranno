package com.tyranno.ssg.order.dto;

import com.tyranno.ssg.global.GlobalCreateTime;
import com.tyranno.ssg.order.domain.OrderList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddDto {

    private List<OptionIdListDto> optionIdList;

    private String deliveryRequest;
    private String deliveryBase;
    private String deliveryDetail;
    private int zipCode;

    private String receiverName;
    private String receiverPhoneNumber;

    private String orderName;
    private String orderPhoneNumber;
    private String orderEmail;

    private int totalMoney;

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
                .orderPhoneNumber(orderAddDto.getOrderPhoneNumber())
                .orderEmail(orderAddDto.getOrderEmail())
                .orderStatus((byte) 0)
                .orderNumber(createdOrderNumber)
                .totalMoney(orderAddDto.getTotalMoney())
                .isOrderConfirm((byte) 0)
                .build();




    }
}
