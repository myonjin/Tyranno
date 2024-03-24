package com.tyranno.ssg.auth.dto;

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
}
