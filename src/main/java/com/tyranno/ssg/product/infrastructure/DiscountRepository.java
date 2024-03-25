package com.tyranno.ssg.product.infrastructure;

import com.tyranno.ssg.product.domain.Discount;
import com.tyranno.ssg.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    Discount findByProductId(Long id);
}
