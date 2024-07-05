package com.tyranno.ssg.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessInfoDto {
    private String id;
    private String pgToken;
}
