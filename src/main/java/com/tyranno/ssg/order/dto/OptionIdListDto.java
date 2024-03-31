package com.tyranno.ssg.order.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OptionIdListDto {

    private long optionId;
    private int count;
    private int money;

}
