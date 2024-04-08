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

    private String password;

    private String phoneNumber;

    private String email;

    public Users toEntity(Users users) {
        return Users.builder()
                .id(users.getId())
                .loginId(users.getLoginId())
                .password((password != null) ? users.hashPassword(password) : users.getPassword())
                .name(users.getName())
                .email(email != null ? email : users.getEmail())
                .gender(users.getGender())
                .phoneNumber(phoneNumber != null ? phoneNumber : users.getPhoneNumber())
                .birth(users.getBirth())
                .status(users.getStatus())
                .isRegistered(users.getIsRegistered())
                .uuid(users.getUuid())
                .build();

    }
}
