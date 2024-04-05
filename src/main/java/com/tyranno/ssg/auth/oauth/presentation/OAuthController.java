package com.tyranno.ssg.auth.oauth.presentation;

import com.tyranno.ssg.auth.oauth.application.OAuthService;
import com.tyranno.ssg.auth.oauth.dto.OAuthExternalIdDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthSignUpDto;
import com.tyranno.ssg.global.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "소셜 (간편 로그인)", description = "OAuth API")
@RequestMapping("/api/v1/auth/oauth")
public class OAuthController {

    private final OAuthService oAuthService;

    @Operation(summary = "소셜 회원 여부 체크", description = "이미 소셜가입한 이용자인지 확인한다.")
    @PostMapping("/check")
    public ResponseEntity<?> checkOAuth(@RequestBody OAuthExternalIdDto oAuthExternalIdDto) {
        oAuthService.checkOAuth(oAuthExternalIdDto);
        return new ResponseEntity<>("소셜 가입되지 않은 이용자입니다.");
    }
    @Operation(summary = "소셜 회원가입", description = "소셜 회원 가입을 한다.")
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody OAuthSignUpDto oAuthSignUpDto) {

        return new ResponseEntity<>(oAuthService.signUpOAuth(oAuthSignUpDto));
    }

    @Operation(summary = "소셜 로그인", description = "소셜 로그인을 한다.")
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody OAuthExternalIdDto oAuthExternalIdDto) {

        return new ResponseEntity<>(oAuthService.loginOAuth(oAuthExternalIdDto));
    }

}

