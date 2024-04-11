package com.tyranno.ssg.order.dto;


import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.order.domain.Order;
import com.tyranno.ssg.order.domain.OrderList;
import java.util.List;
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


    public Order toEntity(OrderList orderList, Option option, OptionIdListDto optionIdListDto){
        return Order.builder()
                .option(option)
                .orderList(orderList)
                .count(optionIdListDto.getCount())
                .money(optionIdListDto.getMoney())
                .isReview((byte)99)
                .build();

    }

}
