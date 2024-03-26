package com.tyranno.ssg.auth.presentation;

import com.tyranno.ssg.auth.application.AuthService;
import com.tyranno.ssg.auth.dto.*;
import com.tyranno.ssg.global.ResponseEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//new ResponseEntity<>(message, headers, HttpStatus.OK);
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDto signUpDto) {

        authService.createUsers(signUpDto);

        return new ResponseEntity<>("회원가입 완료");
    }

    @PostMapping("/id_check")
    public ResponseEntity<?> checkIdExist(@RequestBody IdCheckDto idCheckDto) {

        authService.checkLoginId(idCheckDto);

        return new ResponseEntity<>("사용할 수 있는 아이디입니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@Valid @RequestBody LoginDto logInDto) {

        return new ResponseEntity<>(authService.loginUsers(logInDto));
    }

    @PostMapping("/find-id")
    public ResponseEntity<?> findId(@Valid @RequestBody UserIdentifyDto userIdentifyDto) {

        return new ResponseEntity<>(authService.findLoginId(userIdentifyDto));
    }

    @PutMapping("/change_pw")
    public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordChangeDto passwordChangeDto) {
        authService.changePassword(passwordChangeDto);
        return new ResponseEntity<>("비밀번호 변경 성공");
    }

}
