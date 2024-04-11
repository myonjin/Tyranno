package com.tyranno.ssg.users.dto;

import com.tyranno.ssg.config.ValidationGroups;
import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersModifyDto { //회원 인증 정보 - 유저가 입력, 비밀번호 변경 시 사용

    @Pattern(regexp = "^$|^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,20}$",
            message = "비밀번호는 영문과 숫자 조합으로 8 ~ 20자리까지 가능합니다.")
    private String password;

    @Pattern(regexp = "^$|^01[0-9]-\\d{4}-\\d{4}$",
            message = "휴대폰 번호 형식이 올바르지 않습니다.")
    private String phoneNumber;

    @Pattern(regexp = "^$|^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$",
            message = "올바르지 않은 이메일 형식입니다.")
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
