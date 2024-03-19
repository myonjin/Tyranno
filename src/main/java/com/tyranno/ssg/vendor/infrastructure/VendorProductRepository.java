package com.tyranno.ssg.vendor.infrastructure;

import com.tyranno.ssg.vendor.domain.VendorProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorProductRepository extends JpaRepository<VendorProduct, Long> {
    VendorProduct findByProductId(Long id);
}
