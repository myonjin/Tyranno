package com.tyranno.ssg.category.application;

import com.tyranno.ssg.category.domain.Category;
import com.tyranno.ssg.category.dto.CategoryDto;
import com.tyranno.ssg.category.infrastructure.CategoryRepository;
import com.tyranno.ssg.product.domain.ProductThum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAllDetailId(Long detailId) {
        List<Category> categories = categoryRepository.findAllByDetailId(detailId);
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category : categories) {
            CategoryDto categoryDto = CategoryDto.builder()
                    .productId(category.getProduct().getId()) // 단일 제품 ID 설정
                    .detailId(category.getDetailId())
                    .smallId(category.getSmallId())
                    .middleId(category.getMiddleId())
                    .largeId(category.getLargeId())
                    .build();

            categoryDtos.add(categoryDto);
        }

        return categoryDtos;
    }
}
