package com.tyranno.ssg.order.application;


import com.tyranno.ssg.order.domain.Order;
import com.tyranno.ssg.order.domain.OrderList;
import com.tyranno.ssg.order.dto.OrderAddDto;
import com.tyranno.ssg.order.infrastructure.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void addOrder(OrderAddDto orderAddDto, String uuid) {

        OrderList orderList = orderAddDto.toEntity(uuid, orderAddDto, "99");
        String createdOrderNumber = createOrderNumber(orderList);
        // 짜침 1. 주문번호 생성
        orderList = orderAddDto.toEntity(uuid, orderAddDto, createdOrderNumber);
        // 짜침 2.
//        OrderList(createdOrderNumber);
        orderRepository.save(orderList);

    }
    private String createOrderNumber(OrderList orderList) {
        orderList.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return orderList.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
