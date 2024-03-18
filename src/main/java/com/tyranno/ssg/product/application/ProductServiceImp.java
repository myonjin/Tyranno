package com.tyranno.ssg.product.application;

import com.tyranno.ssg.product.dto.ProductDto;
import com.tyranno.ssg.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public ProductDto productDetail(@PathVariable("productId") Long id) {


        return null;
    }
}
