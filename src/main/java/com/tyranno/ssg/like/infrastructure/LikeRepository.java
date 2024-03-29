package com.tyranno.ssg.like.infrastructure;

<<<<<<< HEAD
import com.tyranno.ssg.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByProductIdAndUsersId(Long productId, Long usersId);
=======
import com.tyranno.ssg.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Cart, Long> {
>>>>>>> cb5a1269a42d364d47354014b302c03e3bee4d94
}
