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
public class DeliveryListDto {
    private Long id;

    private int isBaseDelivery;

    private String deliveryName;

    private Integer zipCode;

    private String deliveryBase;

    private String deliveryDetail;

    private String receiverName;

    private String phoneNumber;

    public static DeliveryListDto fromEntity(Delivery delivery) {
        return DeliveryListDto.builder()
                .id(delivery.getId())
                .isBaseDelivery(delivery.getIsBaseDelivery())
                .deliveryName(delivery.getDeliveryName())
                .zipCode(delivery.getZipCode())
                .deliveryBase(delivery.getDeliveryBase())
                .deliveryDetail(delivery.getDeliveryDetail())
                .receiverName(delivery.getReceiverName())
                .phoneNumber(delivery.getPhoneNumber())
                .build();
    }
}
