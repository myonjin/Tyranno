package com.tyranno.ssg.auth.oauth.presentation;

import com.tyranno.ssg.auth.dto.UsersTypeInfoDto;
import com.tyranno.ssg.auth.oauth.application.OAuthService;
import com.tyranno.ssg.auth.oauth.dto.OAuthExternalIdDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthInfoDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthSignUpDto;
import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.users.domain.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "소셜 (간편 로그인)", description = "OAuth API")
@RequestMapping("/api/v1/auth/oauth")
public class OAuthController {

    private final OAuthService oauthService;

    @Operation(summary = "기존 회원 여부 조회 (소셜 아이디)", description = "소셜 아이디로 기존 회원여부를 조회한다.")
    @PostMapping("/check")
    public ResponseEntity<UsersTypeInfoDto> checkOAuthUsers(@RequestBody OAuthInfoDto oauthInfoDto) {
        return new ResponseEntity<>(oauthService.checkOAuthUsersByOAuthId(oauthInfoDto));
    }
    @Operation(summary = "기존 통합회원 소셜 로그인 연결", description = "기존의 통합 회원이 소셜 회원가입을 한다.")
    @PostMapping("/connect")
    public ResponseEntity<?> connectOAuth(@RequestBody OAuthInfoDto oauthInfoDto) {
        oauthService.connectOAuth(oauthInfoDto);
        return new ResponseEntity<>("기존 통합회원, 소셜 회원가입 완료하였습니다.");
    }

    @Operation(summary = "소셜 회원가입", description = "소셜 회원 가입을 한다.")
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody OAuthSignUpDto oauthSignUpDto) {
        System.out.println("OAuth External ID: " + oauthSignUpDto.getOauthExternalId());
        oauthService.signUpOAuth(oauthSignUpDto);
        return new ResponseEntity<>("소셜 회원가입이 완료되었습니다.");
    }

    @Operation(summary = "소셜 로그인", description = "소셜 로그인을 한다.")
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody OAuthExternalIdDto oauthExternalIdDto) {

        return new ResponseEntity<>(oauthService.loginOAuth(oauthExternalIdDto));
    }

}

