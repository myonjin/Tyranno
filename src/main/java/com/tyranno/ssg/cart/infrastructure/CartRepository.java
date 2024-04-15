package com.tyranno.ssg.cart.infrastructure;

import com.tyranno.ssg.cart.domain.Cart;
import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.users.domain.Users;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUsersUuid(String uuid);
    List<Cart> findByUsersUuidAndIsKeep(String uuid, Byte isKeep);
    boolean existsByOptionAndUsers(Option option, Users users);
    Cart findByOptionAndUsers(Option option, Users users); // 먼저 existsByOption 로 검사해서 optional 형식으로 리턴하지 않음
    void deleteByIdIn(List<Long> ids);

    @Query("SELECT COUNT(c) FROM Cart c WHERE c.users.uuid = :uuid")
    int countByUserUuid(@Param("uuid") String uuid);

}
