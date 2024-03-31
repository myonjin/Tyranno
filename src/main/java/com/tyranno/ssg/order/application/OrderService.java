package com.tyranno.ssg.order.application;

import com.tyranno.ssg.order.dto.OrderAddDto;

public interface OrderService {
    void addOrder(OrderAddDto orderAddDto, String uuid);
}
