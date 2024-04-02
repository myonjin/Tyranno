package com.tyranno.ssg.cart.infrastructure;

import com.tyranno.ssg.cart.domain.Cart;
import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUsersUuid(String uuid);

    boolean existsByOptionAndUsers(Option option, Users users);
    Cart findByOptionAndUsers(Option option, Users users); // 먼저 existsByOption 로 검사해서 optional 형식으로 리턴하지 않음
    void deleteByIdIn(List<Long> ids);

}
