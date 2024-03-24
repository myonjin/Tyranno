package com.tyranno.ssg.category.infrastructure;

import com.tyranno.ssg.category.domain.Category;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByDetailId(Long detailId);

    Optional<Category> findByProductId(Long productId);

}
