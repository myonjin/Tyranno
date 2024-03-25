package com.tyranno.ssg.auth.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
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

//    @Notnull
//    private int snsLogin;

}
