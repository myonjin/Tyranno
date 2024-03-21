package com.tyranno.ssg.text;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsCertificationDto {
    private String phoneNumber;
    private String randomNumber;
}
