package com.tyranno.ssg.auth.presentation;

import com.tyranno.ssg.auth.application.AuthService;
import com.tyranno.ssg.auth.dto.UsersModifyDto;
import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PutMapping("/shinsegae_marketing/{isagree}")
    public ResponseEntity<?> modifyShinsegaeMaketing(@PathVariable Byte isagree, Authentication authentication) {
        String uuid = authentication.getName();
        authService.modifyShinsegaeMaketing(isagree, uuid);
        return new ResponseEntity<>("신세계 동의 변경 성공");
    }

    @PutMapping("/ssg_marketing/{isagree}")
    public ResponseEntity<?> modifySsgMaketing(@PathVariable Byte isagree, Authentication authentication) {
        String uuid = authentication.getName();
        authService.modifySsgMaketing(isagree, uuid);
        return new ResponseEntity<>("ssg 동의 변경 성공");
    }
    @PutMapping
    public ResponseEntity<?> modifyUsers(@RequestBody UsersModifyDto usersModifyDto, Authentication authentication) {
        String uuid = authentication.getName();
        authService.modifyUsers(usersModifyDto, uuid);
        return new ResponseEntity<>("회원 정보 수정 완료");
    }

    @GetMapping
    public ResponseEntity<?> getUsersInfo(Authentication authentication) {
        String uuid = authentication.getName();
        return new ResponseEntity<>(authService.getUsersInfo(uuid));
    }

    @DeleteMapping
    public ResponseEntity<?> resignUsers(Authentication authentication) {
        String uuid = authentication.getName();
        authService.resignUsers(uuid);
        return new ResponseEntity<>("회원 탈퇴 성공");
    }

}
