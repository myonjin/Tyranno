package com.tyranno.ssg.delivery.dto;

import com.tyranno.ssg.config.ValidationGroups;
import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddDto {
    @NotBlank(message = "모든 항목을 입력해주세요.")
    private String deliveryName;

    @NotNull
    private Integer zipCode;

    @NotBlank(message = "모든 항목을 입력해주세요.")
    private String deliveryBase;

    private String deliveryDetail;

    @NotBlank(message = "모든 항목을 입력해주세요.")
    private String receiverName;

    @NotBlank(message = "모든 항목을 입력해주세요.")
    @Pattern(regexp = "^01[0-9]-\\d{4}-\\d{4}$",
            message = "휴대폰 번호 형식이 올바르지 않습니다.")
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
