package com.tyranno.ssg.auth.oauth.application;

import com.tyranno.ssg.auth.oauth.dto.OAuthInfoDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthSignUpDto;

public interface OAuthService {
    // 기존 회원 여부 조회 (소셜 아이디)
    String loginOAuth(OAuthInfoDto oauthInfoDto);
   // void connectOAuth(OAuthInfoDto oauthInfoDto);
   String signUpOAuth(OAuthSignUpDto oauthSignUpDto);
  //  String loginOAuth(OAuthExternalIdDto oauthExternalIdDto);
}
