package com.tyranno.ssg.category.infrastructure;

import com.tyranno.ssg.category.domain.CategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Long> {
    Optional<CategoryDetail> findById(Long id);
}
