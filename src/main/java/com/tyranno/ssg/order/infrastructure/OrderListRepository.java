package com.tyranno.ssg.order.infrastructure;

import com.tyranno.ssg.order.domain.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {

    Optional<OrderList> findByUuid(String uuid);

}
