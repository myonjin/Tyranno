package com.tyranno.ssg.category.application;

import com.tyranno.ssg.category.dto.DetailCategoryDto;
import com.tyranno.ssg.category.dto.LargeCategoryDto;
import com.tyranno.ssg.category.dto.MiddleCategoryDto;
import com.tyranno.ssg.category.dto.SmallCategoryDto;

import java.util.List;
public interface CategoryService {
    List<Long> getProductIdsByLargeId(Long largeId);
    List<Long> getProductIdsByMiddleId(Long middleId);
    List<Long> getProductIdsBySmallId(Long smallId);
    List<Long> getProductIdsByDetailId(Long detailId);

    List<LargeCategoryDto> getLargeCategory();
    List<MiddleCategoryDto> getMiddleCategory(Long largeId);
    List<SmallCategoryDto> getSmallCategory(Long middleId);
    List<DetailCategoryDto> getDetailCategory(Long smallId);
}
