package com.tyranno.ssg.auth.oauth.dto;

import com.tyranno.ssg.auth.oauth.domain.OAuth;
import com.tyranno.ssg.users.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthInfoDto {

    private String name;

    private String email;

    private Long oauthExternalId;

    public OAuth toEntity(Users users) {
        return OAuth.builder()
                .type((byte) 0) // 카카오
                .externalId(oauthExternalId) // externalId 설정
                .users(users)
                .build();
    }

}
