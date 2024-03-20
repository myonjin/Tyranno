package com.tyranno.ssg.product.application;
import com.tyranno.ssg.product.dto.ProductDetailDto;
import com.tyranno.ssg.product.dto.ProductDto;
import com.tyranno.ssg.product.dto.ProductListDto;

import java.util.List;

public interface ProductService {


    ProductDetailDto productDetail(Long id);
    ProductListDto productList();
}
