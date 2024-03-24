package com.tyranno.ssg.category.application;

import com.tyranno.ssg.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<Long> getProductIdsByLargeId(Long largeId);
    List<Long> getProductIdsByMiddleId(Long middleId);
    List<Long> getProductIdsBySmallId(Long smallId);
    List<Long> getProductIdsByDetailId(Long detailId);
}
