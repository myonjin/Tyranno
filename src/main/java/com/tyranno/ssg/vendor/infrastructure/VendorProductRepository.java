package com.tyranno.ssg.vendor.infrastructure;

import com.tyranno.ssg.vendor.domain.VendorProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorProductRepository extends JpaRepository<VendorProduct, Long> {
    Optional<VendorProduct> findByProductId(Long id);
}
