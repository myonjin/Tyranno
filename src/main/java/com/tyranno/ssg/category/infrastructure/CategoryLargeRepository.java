package com.tyranno.ssg.category.infrastructure;

import com.tyranno.ssg.category.domain.CategoryDetail;
import com.tyranno.ssg.category.domain.CategoryLarge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryLargeRepository extends JpaRepository<CategoryLarge, Long> {
    Optional<CategoryLarge> findById(Long id);
}
