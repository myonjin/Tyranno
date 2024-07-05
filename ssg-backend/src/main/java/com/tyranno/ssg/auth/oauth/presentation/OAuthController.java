package com.tyranno.ssg.auth.oauth.presentation;

import com.tyranno.ssg.auth.oauth.application.OAuthService;
import com.tyranno.ssg.auth.oauth.dto.OAuthInfoDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthSignUpDto;
import com.tyranno.ssg.config.ValidationSequence;
import com.tyranno.ssg.global.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "소셜 (간편 로그인)", description = "OAuth API")
@RequestMapping("/api/v1/auth/oauth")
public class OAuthController {

    private final OAuthService oauthService;

    @Operation(summary = "소셜 로그인", description = "기존 소셜회원은 토큰을 리턴하고(로그인), 통합회원은 소셜과 연결하고 토큰을 리턴하고, 비회원은 에러를 리턴한다.")
    @PostMapping("/login")
    public ResponseEntity<String> loginOAuth(@RequestBody OAuthInfoDto oauthInfoDto) {
        return new ResponseEntity<>(oauthService.loginOAuth(oauthInfoDto));
    }

    @Operation(summary = "소셜 회원가입", description = "비회원이 소셜 회원 가입을 한다.")
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Validated(ValidationSequence.class)
                                        @RequestBody OAuthSignUpDto oauthSignUpDto) {

        return new ResponseEntity<>(oauthService.signUpOAuth(oauthSignUpDto));
    }

//    @Operation(summary = "기존 통합회원 소셜 로그인 연결", description = "기존의 통합 회원이 소셜 회원가입을 한다.")
//    @PostMapping("/connect")
//    public ResponseEntity<?> connectOAuth(@RequestBody OAuthInfoDto oauthInfoDto) {
//        oauthService.connectOAuth(oauthInfoDto);
//        return new ResponseEntity<>("기존 통합회원, 소셜 회원가입 완료하였습니다.");
//    }

//    @Operation(summary = "소셜 로그인", description = "소셜 로그인을 한다.")
//    @PostMapping("/login")
//    public ResponseEntity<?> logIn(@RequestBody OAuthExternalIdDto oauthExternalIdDto) {
//
//        return new ResponseEntity<>(oauthService.loginOAuth(oauthExternalIdDto));
//    }

}

