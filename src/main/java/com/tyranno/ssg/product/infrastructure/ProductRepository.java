package com.tyranno.ssg.product.infrastructure;

import com.tyranno.ssg.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    @Query("SELECT p FROM Product p " +
            "WHERE (:largeId IS NULL OR EXISTS(SELECT 1 FROM Category c WHERE c.product = p AND c.largeId = :largeId)) " +
            "AND (:middleId IS NULL OR EXISTS(SELECT 1 FROM Category c WHERE c.product = p AND c.middleId = :middleId)) " +
            "AND (:smallId IS NULL OR EXISTS(SELECT 1 FROM Category c WHERE c.product = p AND c.smallId = :smallId)) " +
            "AND (:detailId IS NULL OR EXISTS(SELECT 1 FROM Category c WHERE c.product = p AND c.detailId = :detailId)) " +
            "ORDER BY " +
            "CASE WHEN :sortCriterion = '1' THEN p.productPrice END ASC, " +
            "CASE WHEN :sortCriterion = '2' THEN p.productPrice END DESC, " +
            "CASE WHEN :sortCriterion = '3' THEN (SELECT d.discount FROM Discount d WHERE d.product = p) END DESC, " +
            "CASE WHEN :sortCriterion = '4' THEN p.reviewCount END DESC, " +
            "CASE WHEN :sortCriterion = '5' THEN p.id END ASC")
    List<Product> findByCategoryIdsAndSortCriterion(
            Long largeId,
            Long middleId,
            Long smallId,
            Long detailId,
            String sortCriterion
    );
}
