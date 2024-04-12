package com.tyranno.ssg.delivery.dto;

import com.tyranno.ssg.delivery.domain.Delivery;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryModifyDto {
    @NotNull
    private Long id;

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

    public Delivery toEntity(Delivery delivery) {
        return Delivery.builder()
                .id(delivery.getId())
                .users(delivery.getUsers())
                .isBaseDelivery(delivery.getIsBaseDelivery())
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
