package com.tyranno.ssg.delivery.dto;

import com.tyranno.ssg.delivery.domain.Delivery;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDeliveryModifyDto {

    private Long deliveryId;

    public Delivery toEntity(Delivery delivery,Byte isBaseDelivery) {
        return Delivery.builder()
                .id(delivery.getId())
                .users(delivery.getUsers())
                .isBaseDelivery(isBaseDelivery)
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
