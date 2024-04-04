package com.tyranno.ssg.auth.oauth.application;

import com.tyranno.ssg.auth.dto.*;
import com.tyranno.ssg.auth.oauth.dto.OAuthSignUpDto;

public interface OAuthService {
    String signUpOAuth(OAuthSignUpDto oAuthSignUpDto);
    String loginUsers(LoginDto loginDto);
}
