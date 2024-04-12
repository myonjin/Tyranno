package com.tyranno.ssg.delivery.infrastructure;

import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByUsersUuid(String uuid);

    Optional<Delivery> findByIsBaseDeliveryAndUsersUuid(Byte isBaseDelivery, String uuid);

}
