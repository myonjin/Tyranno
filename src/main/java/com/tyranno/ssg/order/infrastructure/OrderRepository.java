package com.tyranno.ssg.order.infrastructure;

import com.tyranno.ssg.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
