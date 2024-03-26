package com.tyranno.ssg.users.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.security.JwtTokenProvider;
import com.tyranno.ssg.users.application.UsersService;
import com.tyranno.ssg.users.dto.MarketingModifyDto;
import com.tyranno.ssg.users.dto.PasswordModifyDto;
import com.tyranno.ssg.users.dto.UsersModifyDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UsersController {

    private final UsersService usersService;
    private final JwtTokenProvider jwtTokenProvider;

    @PutMapping("/modify_pw")
    public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordModifyDto passwordModifyDto, @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.headerToUuid(token);
        usersService.modifyPassword(passwordModifyDto, uuid);
        return new ResponseEntity<>("비밀번호 변경 성공");
    }

    @PutMapping("/shinsegae_marketing")
    public ResponseEntity<?> modifyShinsegaeMaketing(@Valid @RequestBody MarketingModifyDto marketingModifyDto, @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.headerToUuid(token);
        usersService.modifyShinsegaeMaketing(marketingModifyDto, uuid);
        return new ResponseEntity<>("신세계 동의 변경 성공");
    }

    @PutMapping("/ssg_marketing")
    public ResponseEntity<?> modifySsgMaketing(@Valid @RequestBody MarketingModifyDto marketingModifyDto, @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.headerToUuid(token);
        usersService.modifySsgMaketing(marketingModifyDto, uuid);
        return new ResponseEntity<>("ssg 동의 변경 성공");
    }

    @PutMapping
    public ResponseEntity<?> modifyUsers(@Valid @RequestBody UsersModifyDto usersModifyDto, @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.headerToUuid(token);
        usersService.modifyUsers(usersModifyDto, uuid);
        return new ResponseEntity<>("회원 정보 수정 완료");
    }

    @GetMapping
    public ResponseEntity<?> getUsersInfo(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.headerToUuid(token);
        return new ResponseEntity<>(usersService.getUsersInfo(uuid));
    }

    @DeleteMapping
    public ResponseEntity<?> resignUsers(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.headerToUuid(token);
        usersService.resignUsers(uuid);
        return new ResponseEntity<>("회원 탈퇴 성공");
    }


}
