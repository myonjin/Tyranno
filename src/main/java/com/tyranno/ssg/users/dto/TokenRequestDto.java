package com.tyranno.ssg.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequestDto { //토큰 갱신 요청을 할 때, 사용될 리프레시 토큰을 서버에 줄때
    private String refreshToken;
}
