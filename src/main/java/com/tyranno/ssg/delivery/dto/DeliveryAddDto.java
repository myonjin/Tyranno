package com.tyranno.ssg.delivery.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddDto {
    @NotNull
    private String deliveryName;
    @NonNull
    private Integer zipCode;
    @NonNull
    private String deliveryBase;
    @NonNull
    private String deliveryDetail;
    @NonNull
    private String receiverName;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String homeNumber;
}
