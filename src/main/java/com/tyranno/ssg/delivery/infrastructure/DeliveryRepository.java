package com.tyranno.ssg.delivery.infrastructure;

import com.tyranno.ssg.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
