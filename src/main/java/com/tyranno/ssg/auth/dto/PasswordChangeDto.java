package com.tyranno.ssg.auth.dto;

import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PasswordChangeDto {
    @NotNull
    private String loginId;
    @NotNull
    private String newPassword;

    public Users toEntity(Users users) {
        return Users.builder()
                .id(users.getId())
                .loginId(users.getLoginId())
                .password(users.hashPassword(newPassword))
                .name(users.getName())
                .email(users.getEmail())
                .gender(users.getGender())
                .phoneNumber(users.getPhoneNumber())
                .birth(users.getBirth())
                .status(users.getStatus())
                .isIntegrated(users.getIsIntegrated())
                .uuid(users.getUuid())
                .build();
    }
}
