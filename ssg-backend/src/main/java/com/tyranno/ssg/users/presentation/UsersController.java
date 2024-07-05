package com.tyranno.ssg.users.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.security.JwtTokenProvider;
import com.tyranno.ssg.users.application.UsersService;
import com.tyranno.ssg.users.dto.MarketingIsAgreeDto;
import com.tyranno.ssg.users.dto.PasswordModifyDto;
import com.tyranno.ssg.users.dto.UsersModifyDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.tyranno.ssg.config.ValidationSequence;


@RequiredArgsConstructor
@RestController
@Tag(name = "회원", description = "Users API")
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UsersService usersService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "사용자 이름 조회", description = "유저 이름을 조회한다.")
    @GetMapping("/name")
    public ResponseEntity<?> getUsersName(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        return new ResponseEntity<>(usersService.getUserName(uuid));
    }

    @Operation(summary = "비밀번호 재설정(로그인 O)", description = "마이페이지에서 비밀번호 재설정한다.")
    @PutMapping("/modify-pw")
    public ResponseEntity<?> changePassword(@Validated(ValidationSequence.class)
                                                @RequestBody PasswordModifyDto passwordModifyDto,
                                            @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        usersService.modifyPassword(passwordModifyDto, uuid);
        return new ResponseEntity<>("비밀번호 변경 성공");
    }

    @Operation(summary = "마케팅 동의 여부 변경", description = "신세계옵션 동의(/marketing/2), ssg 마케팅정보(/marketing/3) 동의 여부를 변경한다.")
    @PutMapping("/marketing/{marketing_id}")
    public ResponseEntity<?> modifyShinsegaeMaketing(@Valid @RequestBody MarketingIsAgreeDto marketingModifyDto,
                                                     @PathVariable Long marketing_id,
                                                     @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        usersService.modifyMarketing(marketingModifyDto, marketing_id, uuid);
        return new ResponseEntity<>("신세계 동의 변경 성공");
    }

    @Operation(summary = "마케팅 동의 여부 조회", description = "신세계옵션 동의(/marketing/2), ssg 마케팅정보(/marketing/3) 동의 여부를 조회한다.")
    @GetMapping("/marketing/{marketing_id}")
    public ResponseEntity<MarketingIsAgreeDto> getMaketingAgree(@PathVariable Long marketing_id,
                                                                @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        return new ResponseEntity<>(usersService.getMarketingAgree(marketing_id, uuid));
    }

    @Operation(summary = "회원수정", description = "마이페이지에서 회원정보 변경한다.")
    @PutMapping
    public ResponseEntity<?> modifyUsers(@Valid @RequestBody UsersModifyDto usersModifyDto,
                                         @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        usersService.modifyUsers(usersModifyDto, uuid);
        return new ResponseEntity<>("회원 정보 수정 완료");
    }

    @Operation(summary = "회원 정보 조회", description = "회원정보 변경 시 기존 정보를 창에 띄우기 위한 api")
    @GetMapping
    public ResponseEntity<?> getUsersInfo(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        return new ResponseEntity<>(usersService.getUsersInfo(uuid));
    }

    @Operation(summary = "회원 탈퇴", description = "회원 상태를 탈퇴로 변경한다.")
    @DeleteMapping
    public ResponseEntity<?> resignUsers(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        usersService.resignUsers(uuid);
        return new ResponseEntity<>("회원 탈퇴 성공");
    }


}
