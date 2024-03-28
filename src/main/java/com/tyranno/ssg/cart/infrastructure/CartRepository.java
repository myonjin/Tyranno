package com.tyranno.ssg.cart.infrastructure;

import com.tyranno.ssg.cart.domain.Cart;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUsersUuid(String uuid);
    boolean existsByOption(Option option);
    boolean existsByOptionProduct(Product product);
    void deleteByIdIn(List<Long> ids);
}
