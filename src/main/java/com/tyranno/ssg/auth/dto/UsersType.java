package com.tyranno.ssg.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UsersType {
    INTEGRATED_USERS(1, "통합회원"),
    KAKAO_USERS(2, "카카오회원"),
    NON_USERS(3, "비회원");

    private final int code;
    private final String description;
}
