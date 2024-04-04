package com.tyranno.ssg.recent.infrastructure;

import com.tyranno.ssg.recent.domain.RecentViewedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecentViewedRepository extends JpaRepository<RecentViewedProduct, Long> {
    Optional<RecentViewedProduct> findByUsersId(Long usersId);
}
