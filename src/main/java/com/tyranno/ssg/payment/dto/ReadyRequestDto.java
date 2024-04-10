package com.tyranno.ssg.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadyRequestDto {
    private String item_name;
    private int total_amount;
}
