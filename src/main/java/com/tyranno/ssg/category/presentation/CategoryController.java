package com.tyranno.ssg.category.presentation;

import com.tyranno.ssg.category.application.CategoryService;
import com.tyranno.ssg.category.dto.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<LargeCategoryDto>> getLargeCategory() {
        List<LargeCategoryDto> largeCategoryDtos = categoryService.getLargeCategory();
        if (largeCategoryDtos != null) {
            log.info("대 카테고리 조회");
            return ResponseEntity.ok(largeCategoryDtos);
        } else {
            log.warn("대 카테고리 없음");
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "중 카테고리", description = "중 카테고리 조회하기")
    @GetMapping("/middle/{large_id}")
    public ResponseEntity<List<MiddleCategoryDto>> getMiddleCategory(@PathVariable Long large_id) {
        List<MiddleCategoryDto> middleCategoryDtos = categoryService.getMiddleCategory(large_id);
        if (middleCategoryDtos != null) {
            log.info("largeCategory ID: {}", large_id);
            return ResponseEntity.ok(middleCategoryDtos);
        } else {
            log.warn("category ID 없는거다: {}", large_id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "소 카테고리", description = "소 카테고리 조회하기")
    @GetMapping("/small/{middle_id}")
    public ResponseEntity<List<SmallCategoryDto>> getSmallCategory(@PathVariable Long middle_id) {
        List<SmallCategoryDto> smallCategoryDtos = categoryService.getSmallCategory(middle_id);
        if (smallCategoryDtos != null) {
            log.info("middleCategory ID: {}", middle_id);
            return ResponseEntity.ok(smallCategoryDtos);
        } else {
            log.warn("category ID 없는거다: {}", middle_id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "상세 카테고리", description = "상세 카테고리 조회하기")
    @GetMapping("/detail/{small_id}")
    public ResponseEntity<List<DetailCategoryDto>> getDetailCategory(@PathVariable Long small_id) {
        List<DetailCategoryDto> detailCategoryDtos = categoryService.getDetailCategory(small_id);
        if (detailCategoryDtos != null) {
            log.info("smallCategory ID: {}", small_id);
            return ResponseEntity.ok(detailCategoryDtos);
        } else {
            log.warn("category ID 없는거다: {}", small_id);
            return ResponseEntity.notFound().build();
        }
    }
}