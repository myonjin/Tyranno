package com.tyranno.ssg.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenResponseDto { // 프론트에게 응답값 넘길때 사용
    private String accessToken;
    private String refreshToken;
}

