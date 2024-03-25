package com.tyranno.ssg.product.application;

import com.tyranno.ssg.product.dto.DiscountDto;
import com.tyranno.ssg.product.dto.ProductDetailDto;
import com.tyranno.ssg.product.dto.ProductDto;

import java.util.List;

public interface ProductService {


    ProductDetailDto productDetail(Long id);

    DiscountDto findDiscountByProductId(Long id);

    List<ProductDto> getProductList(List<Long> productIds);

    List<ProductDto> getProductListSorted(List<ProductDto> beforeProductDtoList, String sortBy);
}
