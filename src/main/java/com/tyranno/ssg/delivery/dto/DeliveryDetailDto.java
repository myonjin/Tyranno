package com.tyranno.ssg.delivery.dto;

import com.tyranno.ssg.delivery.domain.Delivery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryDetailDto {

    private String deliveryName;

    private Integer zipCode;

    private String deliveryBase;

    private String deliveryDetail;

    private String receiverName;

    private String phoneNumber;

    private String homeNumber;


    public static DeliveryDetailDto fromEntity(Delivery delivery) {
        return DeliveryDetailDto.builder()
                .deliveryName(delivery.getDeliveryName())
                .zipCode(delivery.getZipCode())
                .deliveryBase(delivery.getDeliveryBase())
                .deliveryDetail(delivery.getDeliveryDetail())
                .receiverName(delivery.getReceiverName())
                .phoneNumber(delivery.getPhoneNumber())
                .homeNumber(delivery.getHomeNumber())
                .build();
    }
}
