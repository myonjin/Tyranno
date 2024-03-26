package com.tyranno.ssg.product.application;

import com.tyranno.ssg.category.dto.CategoryProductIdListDto;
import com.tyranno.ssg.product.dto.DiscountDto;
import com.tyranno.ssg.product.dto.ProductDetailDto;
import com.tyranno.ssg.product.dto.ProductDto;
import com.tyranno.ssg.product.dto.ProductListDto;

import java.util.List;

public interface ProductService {


    ProductDetailDto productDetail(Long id);

    CategoryProductIdListDto productIdList(Long largeId, Long middleId, Long smallId, Long detailId, String sortCriterion);
    ProductDto productInformation(Long productId);
}
