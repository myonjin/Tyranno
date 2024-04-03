package com.tyranno.ssg.category.application;

import com.tyranno.ssg.category.dto.*;

import java.util.List;
public interface CategoryService {

    //    CategoryProductIdListDto getProductIdByCategory();
    List<LargeCategoryDto> getLargeCategory();
    List<MiddleCategoryDto> getMiddleCategory(Long largeId);
    List<SmallCategoryDto> getSmallCategory(Long middleId);
    List<DetailCategoryDto> getDetailCategory(Long smallId);
    CategoryProductIdListDto getProductIdList(Long largeId, Long middleId, Long smallId, Long detailId, String sortCriterion);
}
