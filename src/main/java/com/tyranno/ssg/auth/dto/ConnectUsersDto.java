package com.tyranno.ssg.auth.dto;

import com.tyranno.ssg.global.ValidationGroups;
import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "아이디는 필수 입력값입니다.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$",
            message = "아이디는 영어 또는 숫자로 6 ~ 20자리까지 가능합니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String loginId;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.", groups = ValidationGroups.NotEmptyGroup.class)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,20}$",
            message = "비밀번호는 영문과 숫자 조합으로 8 ~ 20자리까지 가능합니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
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
