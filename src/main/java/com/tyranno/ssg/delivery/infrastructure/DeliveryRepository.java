package com.tyranno.ssg.delivery.infrastructure;

import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.users.domain.Users;
<<<<<<< HEAD
=======
import org.apache.catalina.User;
>>>>>>> 84d0e2912b4c13076122ad8b7e34ff4364be5ddc
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByUsersUuid(String uuid);

    Optional<Delivery> findByIsBaseDeliveryAndUsers(Byte isBaseDelivery, Users users);

}
