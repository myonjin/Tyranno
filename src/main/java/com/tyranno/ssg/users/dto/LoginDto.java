package com.tyranno.ssg.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotNull
    private String loginId;
    @NotNull
    private String password;
}
