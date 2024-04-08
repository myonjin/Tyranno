package com.tyranno.ssg.auth.presentation;

import com.tyranno.ssg.auth.application.AuthService;
import com.tyranno.ssg.auth.dto.*;
import com.tyranno.ssg.global.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//new ResponseEntity<>(message, headers, HttpStatus.OK);
@RequiredArgsConstructor
@RestController
@Tag(name = "모든 사용자", description = "Auth API")
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "기존 회원 여부 조회 (휴대폰 번호)", description = "휴대폰 번호로 소셜, 통합 회원인지를 조회한다.")
    @PostMapping("/check")
    public ResponseEntity<UsersTypeInfoDto> checkOAuthUsers(@RequestBody PhoneNumberDto phoneNumberDto) {

        return new ResponseEntity<>(authService.checkOAuthUsersByPhoneNum(phoneNumberDto));
    }
    @Operation(summary = "기존 소셜 회원 통합회원 연결", description = "기존의 소셜 회원이 통합 회원가입을 한다.")
    @PostMapping("/connect")
    public ResponseEntity<?> connectUsers(@RequestBody ConnectUsersDto connectUsersDto) {

        authService.connectUsers(connectUsersDto);

        return new ResponseEntity<>("기존 소셜회원, 통합 회원가입 완료하였습니다.");
    }
    @Operation(summary = "통합 회원가입", description = "통합 회원가입을 한다.")
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {

        authService.singUpUsers(signUpDto);

        return new ResponseEntity<>("회원가입 완료");
    }

    @Operation(summary = "아이디 중복 검사", description = "회원 가입 시 기존 회원과 아이디 중복 여부 체크")
    @PostMapping("/id-check")
    public ResponseEntity<?> checkIdExist(@RequestBody IdCheckDto idCheckDto) {

        authService.checkLoginId(idCheckDto);

        return new ResponseEntity<>("사용할 수 있는 아이디입니다.");
    }

    @Operation(summary = "이메일 중복 검사", description = "회원 가입 시 기존 회원과 이메일 중복 여부 체크")
    @PostMapping("/email-check")
    public ResponseEntity<?> checkIdExist(@RequestBody EmailCheckDto emailCheckDto) {

        authService.checkEmail(emailCheckDto);

        return new ResponseEntity<>("사용할 수 있는 이메일입니다.");
    }

    @Operation(summary = "통합 회원 로그인", description = "통합 회원 로그인을 한다.")
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LoginDto logInDto) {

        return new ResponseEntity<>(authService.loginUsers(logInDto));
    }

    @Operation(summary = "아이디 찾기 (문자인증 후)", description = "문자인증한 휴대폰번호로 회원 아이디를 조회한다.")
    @PostMapping("/find-id")
    public ResponseEntity<?> findId(@RequestBody PhoneNumberDto phoneNumberDto) {

        return new ResponseEntity<>(authService.getLoginId(phoneNumberDto));
    }

    @Operation(summary = "비밀번호 변경(로그인 x)", description = "비밀번호 찾기 에서의 비밀번호 변경")
    @PutMapping("/change-pw")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        authService.changePassword(passwordChangeDto);
        return new ResponseEntity<>("비밀번호 변경 성공");
    }

//    @Operation(summary = "휴대폰 번호 중복 검사", description = "회원가입 시 문자인증 전, 휴대폰 번호로 이미 가입된 유저인지 판별한다.")
//    @PostMapping("/phone-number-check")
//    public ResponseEntity<String> checkPhoneNumberExist(@RequestBody PhoneNumberDto phoneNumberDto) {
//
//        authService.checkPhoneNumber(phoneNumberDto);
//
//        return new ResponseEntity<>("사용 가능한 휴대폰 번호 입니다.");
//    }

}

