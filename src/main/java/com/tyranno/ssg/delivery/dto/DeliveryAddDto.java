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
    @NotNull
    private Integer zipCode;
    @NotNull
    private String deliveryBase;
    @NotNull
    private String deliveryDetail;
    @NotNull
    private String receiverName;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String homeNumber;
}
