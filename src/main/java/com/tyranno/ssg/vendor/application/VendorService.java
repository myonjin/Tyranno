package com.tyranno.ssg.vendor.application;

import com.tyranno.ssg.vendor.domain.VendorProduct;

public interface VendorService {
    VendorProduct findByProductId(Long productId);
}
