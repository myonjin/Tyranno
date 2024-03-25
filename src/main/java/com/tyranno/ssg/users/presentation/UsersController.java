package com.tyranno.ssg.users.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.users.application.UsersService;
import com.tyranno.ssg.users.dto.UsersModifyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UsersController {

    private final UsersService usersService;

    @PutMapping("/shinsegae_marketing/{isAgree}")
    public ResponseEntity<?> modifyShinsegaeMaketing(@PathVariable Byte isAgree, Authentication authentication) {
        String uuid = authentication.getName();
        usersService.modifyShinsegaeMaketing(isAgree, uuid);
        return new ResponseEntity<>("신세계 동의 변경 성공");
    }

    @PutMapping("/ssg_marketing/{isAgree}")
    public ResponseEntity<?> modifySsgMaketing(@PathVariable Byte isAgree, Authentication authentication) {
        String uuid = authentication.getName();
        usersService.modifySsgMaketing(isAgree, uuid);
        return new ResponseEntity<>("ssg 동의 변경 성공");
    }

    @PutMapping
    public ResponseEntity<?> modifyUsers(@RequestBody UsersModifyDto usersModifyDto, Authentication authentication) {
        String uuid = authentication.getName();
        usersService.modifyUsers(usersModifyDto, uuid);
        return new ResponseEntity<>("회원 정보 수정 완료");
    }

    @GetMapping
    public ResponseEntity<?> getUsersInfo(Authentication authentication) {
        String uuid = authentication.getName();
        return new ResponseEntity<>(usersService.getUsersInfo(uuid));
    }

    @DeleteMapping
    public ResponseEntity<?> resignUsers(Authentication authentication) {
        String uuid = authentication.getName();
        usersService.resignUsers(uuid);
        return new ResponseEntity<>("회원 탈퇴 성공");
    }


}
