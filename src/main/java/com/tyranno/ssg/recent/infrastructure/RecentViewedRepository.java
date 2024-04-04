package com.tyranno.ssg.recent.infrastructure;

import com.tyranno.ssg.recent.domain.RecentViewedProduct;
import com.tyranno.ssg.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecentViewedRepository extends JpaRepository<RecentViewedProduct, Long> {
    List<RecentViewedProduct> findAllByUsersIdAndIsViewOrderByCreatedAtDesc(Long usersId, Byte view);
    boolean existsByProductIdAndUsersAndIsView(Long productId, Users users, int viewing);

    RecentViewedProduct findByProductIdAndUsersAndIsView(Long productId, Users users, int viewing);
}
