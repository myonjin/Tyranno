package com.tyranno.ssg.product.application;

import com.tyranno.ssg.category.dto.CategoryProductIdListDto;
import com.tyranno.ssg.product.dto.*;

import java.util.List;

public interface ProductService {


    ProductDetailDto productDetail(Long id);

    CategoryProductIdListDto productIdList(Long largeId, Long middleId, Long smallId, Long detailId, String sortCriterion);
    ProductDto getProductInformation(Long productId);
    ProductThumDto getProductThumPriority1(Long productId);
}
