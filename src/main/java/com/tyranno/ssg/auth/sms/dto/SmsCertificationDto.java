package com.tyranno.ssg.auth.sms.dto;

import lombok.Getter;

@Getter
public class SmsCertificationDto {
    private String phoneNumber;
    private String randomNumber;
}
