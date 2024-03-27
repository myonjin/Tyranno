package com.tyranno.ssg.users.dto;

import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersModifyDto { //회원 인증 정보 - 유저가 입력, 비밀번호 변경 시 사용
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;

    public Users toEntity(Users users) {
        return Users.builder()
                .id(users.getId())
                .loginId(users.getLoginId())
                .password(users.hashPassword(password))
                .name(users.getName())
                .email(email)
                .gender(users.getGender())
                .phoneNumber(phoneNumber)
                .birth(users.getBirth())
                .status(users.getStatus())
                .uuid(users.getUuid())
                .build();

    }
}
