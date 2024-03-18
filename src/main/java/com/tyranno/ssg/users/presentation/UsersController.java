package com.tyranno.ssg.users.presentation;

import com.tyranno.ssg.security.JwtTokenProvider;
import com.tyranno.ssg.users.application.UsersService;
import com.tyranno.ssg.users.dto.SignUpDto;
import com.tyranno.ssg.users.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
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
public class UsersController {

    private final UsersService usersService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<TokenResponseDto> signUp(@RequestBody SignUpDto signUpDto) {
        usersService.createUsers(signUpDto);

        String accessToken = jwtTokenProvider.generateAccessToken(signUpDto.getLoginId());
        // 리프레시 토큰 생성
        String refreshToken = jwtTokenProvider.generateRefreshToken(signUpDto.getLoginId());

        // DTO를 생성하여 반환
        TokenResponseDto tokenResponseDto = new TokenResponseDto(accessToken, refreshToken);

        return new ResponseEntity<>(tokenResponseDto, HttpStatus.OK);
    }


}
