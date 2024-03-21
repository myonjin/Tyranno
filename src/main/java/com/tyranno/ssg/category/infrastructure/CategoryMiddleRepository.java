package com.tyranno.ssg.category.infrastructure;

import com.tyranno.ssg.category.domain.CategoryDetail;
import com.tyranno.ssg.category.domain.CategoryMiddle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryMiddleRepository extends JpaRepository<CategoryMiddle, Long> {
    Optional<CategoryMiddle> findById(Long id);
}
