package com.tyranno.ssg.users.dto;

import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersModifyDto { //회원 인증 정보 - 유저가 입력, 비밀번호 변경 시 사용

    private String password;

    private String phoneNumber;

    private String email;

    public Users toEntity(Users users) {
        return Users.builder()
                .id(users.getId())
                .loginId(users.getLoginId())
                .password((!password.isEmpty()) ? users.hashPassword(password) : users.getPassword())
                .name(users.getName())
                .email((!email.isEmpty()) ? email : users.getEmail())
                .gender(users.getGender())
                .phoneNumber((!phoneNumber.isEmpty()) ? phoneNumber : users.getPhoneNumber())
                .birth(users.getBirth())
                .status(users.getStatus())
                .isIntegrated(users.getIsIntegrated())
                .uuid(users.getUuid())
                .build();

    }
}
