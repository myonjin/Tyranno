package com.tyranno.ssg.product.application;

import com.tyranno.ssg.category.dto.CategoryProductIdListDto;
import com.tyranno.ssg.product.dto.*;

public interface ProductService {


    ProductDetailDto productDetail(Long id);
    ProductInformationDto getProductInformation(Long productId);
    ProductThumDto getProductThumPriority1(Long productId);
    int getDiscount(Long productId);
}
