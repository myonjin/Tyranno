package com.tyranno.ssg.users.dto;


import com.tyranno.ssg.users.domain.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersInfoDto {
    @NotNull
    private String loginId;
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;

    public static UsersInfoDto FromEntity(Users users) {
        return UsersInfoDto.builder()
                .loginId(users.getLoginId())
                .name(users.getName())
                .phoneNumber(users.getPhoneNumber())
                .email(users.getEmail())
                .build();
    }
}
