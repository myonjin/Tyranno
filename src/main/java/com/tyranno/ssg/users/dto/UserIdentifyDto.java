package com.tyranno.ssg.users.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentifyDto {
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private Byte gender;
    @NotNull
    private LocalDate birth;
}
