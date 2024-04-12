package com.tyranno.ssg.delivery.dto.response;

import com.tyranno.ssg.delivery.domain.Delivery;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseDeliveryInfoDto {

    private String receiverName;

    private String phoneNumber;

    private String deliveryName;

    private Integer zipCode;

    private String deliveryBase;

    private String deliveryDetail;


    public static BaseDeliveryInfoDto fromEntity(Delivery delivery) {
        return BaseDeliveryInfoDto.builder()
                .receiverName(delivery.getReceiverName())
                .phoneNumber(delivery.getPhoneNumber())
                .deliveryName(delivery.getDeliveryName())
                .zipCode(delivery.getZipCode())
                .deliveryBase(delivery.getDeliveryBase())
                .deliveryDetail(delivery.getDeliveryDetail())
                .build();
    }
}
