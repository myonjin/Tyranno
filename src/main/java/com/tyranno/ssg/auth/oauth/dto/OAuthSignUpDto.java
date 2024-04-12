package com.tyranno.ssg.auth.oauth.dto;

import com.tyranno.ssg.auth.oauth.domain.OAuth;
import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.config.ValidationGroups;
import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthSignUpDto {

    @NotBlank(message = "모든 항목을 입력해주세요.")
    private String name;

    @NotBlank(message = "모든 항목을 입력해주세요.")
    private String deliveryBase;

    @NotBlank(message = "모든 항목을 입력해주세요.")
    private String deliveryDetail;

    @NotNull
    private int zipCode;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^01[0-9]-\\d{4}-\\d{4}$",
            message = "휴대폰 번호 형식이 올바르지 않습니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String phoneNumber;

    @NotBlank(message = "이메일을 입력해주세요.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$",
            message = "올바르지 않은 이메일 형식입니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String email;

    @NotNull
    private Byte gender;

    @NotNull
    private LocalDate birth;

    @NotNull
    private Byte shinsegaeMarketingAgree;

    @NotNull
    private Byte shinsegaeOptionAgree;

    @NotNull
    private Byte ssgMarketingAgree;

    @NotNull
    private Long oauthExternalId;

    public OAuth toOAuthEntity(Users users) {
        return OAuth.builder()
                .type((byte) 0) // 카카오
                .externalId(oauthExternalId) // externalId 설정
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
                .isIntegrated((byte) 0) // 통합회원 여부 false
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
