package com.tyranno.ssg.category.infrastructure;

import com.tyranno.ssg.category.domain.Category;

import com.tyranno.ssg.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("SELECT c.product FROM Category c WHERE c.largeId = :largeId")
    List<Product> findProductsByLargeId(@Param("largeId") Long middleId);
    @Query("SELECT c.product FROM Category c WHERE c.middleId = :middleId")
    List<Product> findProductsByMiddleId(@Param("middleId") Long middleId);
    @Query("SELECT c.product FROM Category c WHERE c.smallId = :smallId")
    List<Product> findProductsBySmallId(@Param("smallId") Long smallId);
    @Query("SELECT c.product FROM Category c WHERE c.detailId = :detailId")
    List<Product> findProductsByDetailId(@Param("detailId") Long detailId);

    Optional<Category> findByProductId(Long productId);

}
