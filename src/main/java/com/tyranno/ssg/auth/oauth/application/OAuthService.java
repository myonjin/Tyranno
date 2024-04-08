package com.tyranno.ssg.auth.oauth.application;

import com.tyranno.ssg.auth.dto.UsersTypeInfoDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthExternalIdDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthInfoDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthSignUpDto;
import jakarta.transaction.Transactional;

public interface OAuthService {
    // 기존 회원 여부 조회 (소셜 아이디)
    UsersTypeInfoDto checkOAuthUsersByOAuthId(OAuthInfoDto oauthInfoDto);
    void connectOAuth(OAuthInfoDto oauthInfoDto);
    void signUpOAuth(OAuthSignUpDto oauthSignUpDto);
    String loginOAuth(OAuthExternalIdDto oauthExternalIdDto);
}
