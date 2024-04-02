package com.tyranno.ssg.order.application;

import com.tyranno.ssg.order.dto.OrderAddDto;
import com.tyranno.ssg.order.dto.OrderListDto;

import java.util.List;

public interface OrderService {
    void addOrderList(OrderAddDto orderAddDto, String uuid);

    OrderListDto getOrderList(String uuid);
}
