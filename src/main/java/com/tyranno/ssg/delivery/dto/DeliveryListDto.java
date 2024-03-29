package com.tyranno.ssg.delivery.dto;

import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryListDto {
    @NotNull
    private Long id;
    @NotNull
    private int isBaseDelivery;
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
                .homeNumber(delivery.getHomeNumber())
                .build();


    }
}
