package com.tyranno.ssg.auth.dto;

import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectUsersDto {
    private String phoneNumber;
    @NotNull
    private String loginId;
    @NotNull
    private String password;

    public Users toEntity(Users users) {
        return Users.builder()
                .id(users.getId())
                .loginId(loginId)
                .password(new BCryptPasswordEncoder().encode(password))
                .name(users.getName())
                .email(users.getEmail())
                .gender(users.getGender())
                .phoneNumber(users.getPhoneNumber())
                .birth(users.getBirth())
                .status(users.getStatus())
                .isIntegrated((byte) 1) // 통합회원 여부 true
                .uuid(users.getUuid())
                .build();
    }
}
