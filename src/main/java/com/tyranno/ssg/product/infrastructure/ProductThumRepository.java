package com.tyranno.ssg.product.infrastructure;

import com.tyranno.ssg.product.domain.ProductThum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductThumRepository extends JpaRepository<ProductThum, Long> {

    List<ProductThum> findAllByProductId(Long id);
    Optional<ProductThum> findByProductIdAndPriority(Long id, int priority);
}
