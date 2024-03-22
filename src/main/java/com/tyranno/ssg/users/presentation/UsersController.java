package com.tyranno.ssg.users.presentation;

import com.tyranno.ssg.security.JwtTokenProvider;
import com.tyranno.ssg.users.application.UsersService;
import com.tyranno.ssg.users.dto.LoginDto;
import com.tyranno.ssg.users.dto.SignUpDto;
import com.tyranno.ssg.users.dto.UserIdentifyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//new ResponseEntity<>(message, headers, HttpStatus.OK);
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UsersController {

    private final UsersService usersService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {

        usersService.createUsers(signUpDto);

        return new ResponseEntity<>("메세지", HttpStatus.OK);
    }

    @PostMapping("/id_check")
    public ResponseEntity<?> checkIdExist(@RequestBody String loginId) {

        usersService.checkLoginId(loginId);

        return new ResponseEntity<>("메세지", HttpStatus.OK);
    }

    @PostMapping("/login")
    public String logIn(@RequestBody LoginDto logInDto) {
        return usersService.loginUsers(logInDto);
        //에러처리 후 사용 : return ResponseEntity<>;
    }

    @PostMapping("/find_id")
    public String findId(@RequestBody UserIdentifyDto userIdentifyDto) {
        return usersService.findLoginId(userIdentifyDto);
    }

    @PostMapping("/change_pw")
    public ResponseEntity<?> changePassword(@RequestBody String password) {
        //헤더에서 uuid 추출
        //usersService.changePassword(password, uuid);
        return new ResponseEntity<>("메세지", HttpStatus.OK);
    }

}
