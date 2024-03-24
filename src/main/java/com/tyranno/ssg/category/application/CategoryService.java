package com.tyranno.ssg.category.application;

import com.tyranno.ssg.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
//    CategoryDto sortCategoryDetail(Long CategoryDetailId);
    List<CategoryDto> findAllDetailId(Long detailId);
}
