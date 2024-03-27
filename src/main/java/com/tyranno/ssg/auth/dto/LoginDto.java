package com.tyranno.ssg.auth.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotNull
    private String loginId;
    @NotNull
    private String password;
}
