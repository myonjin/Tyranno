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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "대 카테고리 조회 완료"),
            @ApiResponse(responseCode = "400", description = "대 카테고리 조회 중 오류 발생")})
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
    @GetMapping("/middle/{largeId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "중 카테고리 조회 완료"),
            @ApiResponse(responseCode = "400", description = "중 카테고리 조회 중 오류 발생")})
    public ResponseEntity<List<MiddleCategoryDto>> getMiddleCategory(@PathVariable Long largeId) {
        List<MiddleCategoryDto> middleCategoryDtos = categoryService.getMiddleCategory(largeId);
        if (middleCategoryDtos != null) {
            log.info("largeCategory ID: {}", largeId);
            return ResponseEntity.ok(middleCategoryDtos);
        } else {
            log.warn("category ID 없는거다: {}", largeId);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "소 카테고리", description = "소 카테고리 조회하기")
    @GetMapping("/small/{middleId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "소 카테고리 조회 완료"),
            @ApiResponse(responseCode = "400", description = "소 카테고리 조회 중 오류 발생")})
    public ResponseEntity<List<SmallCategoryDto>> getSmallCategory(@PathVariable Long middleId) {
        List<SmallCategoryDto> smallCategoryDtos = categoryService.getSmallCategory(middleId);
        if (smallCategoryDtos != null) {
            log.info("middleCategory ID: {}", middleId);
            return ResponseEntity.ok(smallCategoryDtos);
        } else {
            log.warn("category ID 없는거다: {}", middleId);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "상세 카테고리", description = "상세 카테고리 조회하기")
    @GetMapping("/detail/{smallId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상세 카테고리 조회 완료"),
            @ApiResponse(responseCode = "400", description = "상세 카테고리 조회 중 오류 발생")})
    public ResponseEntity<List<DetailCategoryDto>> getDetailCategory(@PathVariable Long smallId) {
        List<DetailCategoryDto> detailCategoryDtos = categoryService.getDetailCategory(smallId);
        if (detailCategoryDtos != null) {
            log.info("smallCategory ID: {}", smallId);
            return ResponseEntity.ok(detailCategoryDtos);
        } else {
            log.warn("category ID 없는거다: {}", smallId);
            return ResponseEntity.notFound().build();
        }
    }
}