package com.tyranno.ssg.product.application;
import com.tyranno.ssg.product.domain.Discount;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.dto.DiscountDto;
import com.tyranno.ssg.product.dto.ProductDetailDto;
import com.tyranno.ssg.product.dto.ProductDto;
import com.tyranno.ssg.product.dto.ProductListDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {


    ProductDetailDto productDetail(Long id);

    DiscountDto findDiscountByProductId(Long id);

    List<ProductDto> getProductList(List<Long> productIds);

    List<ProductDto> getProductListSorted(List<ProductDto> beforeProductDtoList, String sortBy);
}
