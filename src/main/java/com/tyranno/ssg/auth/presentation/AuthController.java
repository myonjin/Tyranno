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

    @Operation(summary = "회원가입", description = "회원 가입")
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDto signUpDto) {

        authService.createUsers(signUpDto);

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

    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@Valid @RequestBody LoginDto logInDto) {

        return new ResponseEntity<>(authService.loginUsers(logInDto));
    }

    @Operation(summary = "아이디 찾기", description = "아이디 찾기")
    @PostMapping("/find-id")
    public ResponseEntity<?> findId(@Valid @RequestBody UserIdentifyDto userIdentifyDto) {

        return new ResponseEntity<>(authService.findLoginId(userIdentifyDto));
    }

    @Operation(summary = "비밀번호 변경(로그인 x)", description = "비밀번호 찾기 에서의 비밀번호 변경")
    @PutMapping("/change-pw")
    public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordChangeDto passwordChangeDto) {
        authService.changePassword(passwordChangeDto);
        return new ResponseEntity<>("비밀번호 변경 성공");
    }

}

