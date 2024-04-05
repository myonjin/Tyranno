package com.tyranno.ssg.auth.oauth.dto;

import com.tyranno.ssg.auth.oauth.domain.OAuth;
import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.users.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

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

    private Byte shinsegaeMarketingAgree;

    private Byte shinsegaeOptionAgree;

    private Byte ssgMarketingAgree;

    public OAuth toOAuthEntity(Users users) {
        return OAuth.builder()
                .type((byte) 0) // 카카오
                .externalId(oAuthExternalId) // externalId 설정
                .users(users)
                .build();
    }

    public Users toUsersEntity() {
        return Users.builder()
                .loginId(String.valueOf(email.hashCode()))
                .password(RandomStringUtils.randomAlphanumeric(30))
                .name(name)
                .email(email)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .birth(birth)
                .status(0) // 활동중
                .isRegistered((byte) 1)  // true
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
