package com.tyranno.ssg.auth.oauth.application;

import com.tyranno.ssg.auth.oauth.dto.OAuthExternalIdDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthSignUpDto;

public interface OAuthService {
    String signUpOAuth(OAuthSignUpDto oAuthSignUpDto);
    String loginOAuth(OAuthExternalIdDto oAuthExternalIdDto);
}
