package com.tyranno.ssg.auth.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;


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
