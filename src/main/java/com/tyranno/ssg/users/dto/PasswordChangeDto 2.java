package com.tyranno.ssg.users.dto;

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
}
