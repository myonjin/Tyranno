package com.tyranno.ssg.auth.oauth.dto;

import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthSignUpDto {

    private Long oAuthExternalId;

    private String name;

    private String deliveryBase;

    private String deliveryDetail;

    private int zipCode;

    private String phoneNumber;

    private String email;

    private Byte gender;

    private LocalDate birth;

    private Byte ssgMarketingAgree;

    public Users toUsersEntity() {
        return Users.builder()
                .loginId(loginId)
                .password(new BCryptPasswordEncoder().encode(password))
                .name(name)
                .email(email)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .birth(birth)
                .status(0) // 활동중
                .uuid(UUID.randomUUID().toString())
                .build();
    }

    public Delivery toDeliveryEntity(Users users) {
        return Delivery.builder()
                .users(users)
                .isBaseDelivery((byte) 11)
                .deliveryName(name)
                .zipCode(zipCode)
                .deliveryBase(deliveryBase)
                .deliveryDetail(deliveryDetail)
                .receiverName(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}
