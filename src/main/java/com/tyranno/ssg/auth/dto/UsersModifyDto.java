package com.tyranno.ssg.auth.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersModifyDto { //회원 인증 정보 - 유저가 입력, 비밀번호 변경 시 사용
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
}
