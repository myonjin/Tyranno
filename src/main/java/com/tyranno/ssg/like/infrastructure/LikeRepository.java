package com.tyranno.ssg.like.infrastructure;

import com.tyranno.ssg.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Cart, Long> {
}
