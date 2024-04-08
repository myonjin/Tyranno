package com.tyranno.ssg.auth.dto;

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
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto { // 회원가입 정보 이걸로 받음
    @NotNull  // - 유효성 검사를 위해 넣음
    private String loginId;
    @NotNull
    private String password;
    @NotNull // - 회원 가입시 한 정보라도 안받으면 오류창
    private String name;
    @NotNull
    private String deliveryBase;
    @NotNull
    private String deliveryDetail;
    @NotNull
    private int zipCode;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
    @NotNull
    private Byte gender;
    @NotNull
    private LocalDate birth;

    private Byte shinsegaeMarketingAgree;

    private Byte shinsegaeOptionAgree;

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
                .isIntegrated((byte) 1) // 통합회원 여부 true
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
