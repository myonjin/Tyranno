package com.tyranno.ssg.category.infrastructure;

import com.tyranno.ssg.category.domain.Category;

import com.tyranno.ssg.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Product> findProductsByLargeId(Long largeId);
    List<Product> findProductsByMiddleId(Long middleId);
    List<Product> findProductsBySmallId(Long smallId);
    List<Product> findProductsByDetailId(Long detailId);

    Optional<Category> findByProductId(Long productId);

}
