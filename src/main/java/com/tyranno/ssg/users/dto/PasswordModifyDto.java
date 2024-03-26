package com.tyranno.ssg.users.dto;

import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordModifyDto {
    @NotNull
    private String newPassword;

    public Users toEntity(Users users) {
        return Users.builder()
                .loginId(users.getLoginId())
                .password(users.hashPassword(newPassword))
                .name(users.getName())
                .email(users.getEmail())
                .gender(users.getGender())
                .phoneNumber(users.getPhoneNumber())
                .birth(users.getBirth())
                .status(users.getStatus()) // 활동중
                .uuid(users.getUuid())
                .build();
    }
}
