package com.tyranno.ssg.category.presentation;

import com.tyranno.ssg.category.application.CategoryService;
import com.tyranno.ssg.category.dto.*;

import com.tyranno.ssg.global.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@Tag(name = "카테고리", description = "카테고리 API")
@Slf4j
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "대 카테고리", description = "대 카테고리 조회하기")
    @GetMapping("/")
    public ResponseEntity<?> getLargeCategory() {
        List<LargeCategoryDto> largeCategoryDtos = categoryService.getLargeCategory();
        return new ResponseEntity<>(largeCategoryDtos);
    }
    @Operation(summary = "중 카테고리", description = "중 카테고리 조회하기")
    @GetMapping("/middle/{large_id}")
    public ResponseEntity<?> getMiddleCategory(@PathVariable Long large_id) {
        List<MiddleCategoryDto> middleCategoryDtos = categoryService.getMiddleCategory(large_id);
        return new ResponseEntity<>(middleCategoryDtos);
    }

    @Operation(summary = "소 카테고리", description = "소 카테고리 조회하기")
    @GetMapping("/small/{middle_id}")
    public ResponseEntity<?> getSmallCategory(@PathVariable Long middle_id) {
        List<SmallCategoryDto> smallCategoryDtos = categoryService.getSmallCategory(middle_id);
        return new ResponseEntity<>(smallCategoryDtos);
    }

    @Operation(summary = "상세 카테고리", description = "상세 카테고리 조회하기")
    @GetMapping("/detail/{small_id}")
    public ResponseEntity<?> getDetailCategory(@PathVariable Long small_id) {
        List<DetailCategoryDto> detailCategoryDtos = categoryService.getDetailCategory(small_id);
        return new ResponseEntity<>(detailCategoryDtos);
    }
}