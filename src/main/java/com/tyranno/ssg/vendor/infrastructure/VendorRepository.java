package com.tyranno.ssg.vendor.infrastructure;

import com.tyranno.ssg.vendor.domain.Vendor;
import com.tyranno.ssg.vendor.domain.VendorProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {


    Optional<Vendor> findById(Long id);
    Long findByVendorName(String vendorName);
}
