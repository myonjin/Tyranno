package com.tyranno.ssg.category.infrastructure;

import com.tyranno.ssg.category.domain.Category;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByProductId(Long productId);
    List<Category> findAllByCategoryDetailId(int sortCriterion, Long CategoryDetailId);
    List<Category> findAllByCategorySmallId(int sortCriterion, Long CategorySmallId);
    List<Category> findAllByCategoryMiddleId(int sortCriterion, Long CategoryMiddleId);
    List<Category> findAllByCategoryLargeId(int sortCriterion, Long CategoryLargeId);
//    @Query("SELECT p FROM Category c " +
//            "JOIN c.product p " +
//            "JOIN Discount d ON p.id = d.product.id " +
//            "WHERE c.id = :categoryId " +
//            "ORDER BY " +
//            "CASE WHEN :sortCriterion = 1 THEN p.productPrice END ASC, " +
//            "CASE WHEN :sortCriterion = 2 THEN p.productPrice END DESC, " +
//            "CASE WHEN :sortCriterion = 3 THEN d.discount END DESC, " +
//            "CASE WHEN :sortCriterion = 4 THEN p.reviewCount END DESC, " +
//            "CASE WHEN :sortCriterion = 5 THEN p.id END"
//1 :  낮은 가격 순 order by productPrice ASC
//2 :  높은 가격 순 order by productPrice DESC
//3 :  할인율 순 order by discount DESC
//4 :  리뷰 많은 순 order by reviewCount
//5 :  상품 아이디 순

}
