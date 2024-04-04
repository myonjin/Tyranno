package com.tyranno.ssg.product.application;

import com.tyranno.ssg.product.dto.*;

public interface ProductService {


    ProductDetailDto productDetail(Long id);
    ProductInformationDto getProductInformation(Long productId, String uuid);
    ProductThumDto getProductThumPriority1(Long productId);
    int getDiscount(Long productId);

    ProductIdListDto getProductIdList(Long largeId, Long middleId, Long smallId, Long detailId, Integer sortCriterion,
                                      Integer lastIndex);
}
