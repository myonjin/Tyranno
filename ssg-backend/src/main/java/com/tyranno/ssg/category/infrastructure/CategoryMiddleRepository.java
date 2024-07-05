package com.tyranno.ssg.category.infrastructure;

import com.tyranno.ssg.category.domain.CategoryMiddle;
import com.tyranno.ssg.category.dto.MiddleCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryMiddleRepository extends JpaRepository<CategoryMiddle, Long> {
    List<CategoryMiddle> findByCategoryLargeId(Long id);
}
