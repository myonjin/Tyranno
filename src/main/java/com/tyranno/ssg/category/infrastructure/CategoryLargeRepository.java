package com.tyranno.ssg.category.infrastructure;

import com.tyranno.ssg.category.domain.CategoryDetail;
import com.tyranno.ssg.category.domain.CategoryLarge;
import com.tyranno.ssg.category.domain.CategoryMiddle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryLargeRepository extends JpaRepository<CategoryLarge, Long> {
    List<CategoryLarge> findAll();
}
