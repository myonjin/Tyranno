package com.tyranno.ssg.order.infrastructure;

import com.tyranno.ssg.order.domain.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {


}
