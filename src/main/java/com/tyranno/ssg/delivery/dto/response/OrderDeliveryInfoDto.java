package com.tyranno.ssg.delivery.dto.response;

import com.tyranno.ssg.delivery.domain.Delivery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDeliveryInfoDto {

    private String receiverName;

    private String phoneNumber;

    private String deliveryName;

    private String deliveryBase;

    private String deliveryDetail;

    private Integer zipCode;

    public static OrderDeliveryInfoDto fromEntity(Delivery delivery) {
        return OrderDeliveryInfoDto.builder()
                .receiverName(delivery.getReceiverName())
                .phoneNumber(delivery.getPhoneNumber())
                .deliveryName(delivery.getDeliveryName())
                .deliveryBase(delivery.getDeliveryBase())
                .deliveryDetail(delivery.getDeliveryDetail())
                .zipCode(delivery.getZipCode())
                .build();
    }
}

