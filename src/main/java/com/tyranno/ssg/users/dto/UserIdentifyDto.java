package com.tyranno.ssg.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
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
