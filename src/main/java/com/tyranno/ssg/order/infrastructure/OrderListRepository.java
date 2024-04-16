package com.tyranno.ssg.order.infrastructure;

import com.tyranno.ssg.order.domain.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {

    List<OrderList> findAllByUuidOrderByCreatedAtDesc(String uuid);
    List<OrderList> findByOrderNameAndOrderPhoneNumberAndOrderNumber(String orderName, String orderPhoneNumber, String orderNumber);
}
