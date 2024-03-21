package com.tyranno.ssg.category.application;

import com.tyranno.ssg.category.domain.Category;
import com.tyranno.ssg.category.dto.CategoryDto;
import com.tyranno.ssg.category.infrastructure.CategoryRepository;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.domain.ProductThum;
import com.tyranno.ssg.product.dto.ProductDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto sortCategoryDetail(int sortCriterion, Long CategoryDetailId) {
        List<Category> Categorys = CategoryRepository.findAllByCategoryDetailId(sortCriterion, CategoryDetailId);

    }

}
