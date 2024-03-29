package com.tyranno.ssg.delivery.dto;

import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddDto {
    @NotNull
    private String deliveryName;
    @NotNull
    private Integer zipCode;
    @NotNull
    private String deliveryBase;

    private String deliveryDetail;
    @NotNull
    private String receiverName;
    @NotNull
    private String phoneNumber;

    private String homeNumber;

    public Delivery toEntity(Users users) {
        return Delivery.builder()
                .users(users)
                .isBaseDelivery((byte) 99)
                .deliveryName(deliveryName)
                .zipCode(zipCode)
                .deliveryBase(deliveryBase)
                .deliveryDetail(deliveryDetail)
                .receiverName(receiverName)
                .phoneNumber(phoneNumber)
                .homeNumber(homeNumber)
                .build();
    }
}
