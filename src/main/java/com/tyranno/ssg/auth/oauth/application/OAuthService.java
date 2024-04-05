package com.tyranno.ssg.auth.oauth.application;

import com.tyranno.ssg.auth.oauth.dto.OAuthExternalIdDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthInfoDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthSignUpDto;
import jakarta.transaction.Transactional;

public interface OAuthService {
    // 기존 회원 여부 조회 (소셜 아이디)
    String checkOAuthUsersByOAuthId(OAuthInfoDto oAuthInfoDto);
    void connectOAuth(OAuthInfoDto oAuthInfoDto);
    void signUpOAuth(OAuthSignUpDto oAuthSignUpDto);
    String loginOAuth(OAuthExternalIdDto oAuthExternalIdDto);
}
