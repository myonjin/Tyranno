package com.tyranno.ssg.vendor.application;

import com.tyranno.ssg.vendor.domain.VendorProduct;
import com.tyranno.ssg.vendor.infrastructure.VendorProductRepository;
import com.tyranno.ssg.vendor.infrastructure.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImp implements VendorService{
    private final VendorRepository vendorRepository;
    private final VendorProductRepository vendorProductRepository;
    @Autowired
    public VendorServiceImp(VendorRepository vendorRepository, VendorProductRepository vendorProductRepository) {
        this.vendorRepository = vendorRepository;
        this.vendorProductRepository = vendorProductRepository;
    }

    @Override
    public VendorProduct findByProductId(Long productId) {
        return vendorProductRepository.findByProductId(productId);
    };

}
