package com.tyranno.ssg.category.infrastructure;

import com.tyranno.ssg.category.domain.Category;

import com.tyranno.ssg.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByProductId(Long productId);

}
