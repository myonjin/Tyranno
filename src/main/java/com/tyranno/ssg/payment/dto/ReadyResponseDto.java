package com.tyranno.ssg.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadyResponseDto {
    private String tid;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;
}
