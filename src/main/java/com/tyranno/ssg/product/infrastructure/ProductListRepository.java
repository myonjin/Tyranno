package com.tyranno.ssg.product.infrastructure;
import com.tyranno.ssg.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductListRepository extends JpaRepository<Product, Long>{
    List<Product> findAll();
}
