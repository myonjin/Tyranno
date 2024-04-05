package com.tyranno.ssg.auth.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthInfoDto {
    private Long oAuthExternalId;
    private String name;
    private String email;
}
