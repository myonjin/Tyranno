package com.tyranno.ssg.product.presentation;

import com.tyranno.ssg.category.application.CategoryService;
import com.tyranno.ssg.category.dto.CategoryProductIdListDto;
import com.tyranno.ssg.category.infrastructure.CategoryRepositoryImp;
import com.tyranno.ssg.product.application.ProductService;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.dto.ProductDetailDto;
import com.tyranno.ssg.product.dto.ProductDto;
import com.tyranno.ssg.product.dto.ProductListDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Operation(summary = "상품 상세페이지", description = "상품 상세페이지를 열기", tags = { "Get ProductDetail" })
    @GetMapping("/detail/{productId}")
    public ResponseEntity<ProductDetailDto> productDetail(@PathVariable Long productId) {
        log.info("productDetail 실행");
        log.info(productId.toString());

        ProductDetailDto productDetailDto = productService.productDetail(productId);

        if (productDetailDto != null) {
            log.info("product ID: {}", productId);
            return ResponseEntity.ok(productDetailDto);
        } else {
            log.warn("product ID 없는거다: {}", productId);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "상품 ID 리스트", description = "상품 ID 리스트를 받아오기", tags = { "Get ProductIDList" })
    @GetMapping("/productList")
    public ResponseEntity<CategoryProductIdListDto> ProductIdList(
            @RequestParam(required = false) Long largeId,
            @RequestParam(required = false) Long middleId,
            @RequestParam(required = false) Long smallId,
            @RequestParam(required = false) Long detailId,
            @RequestParam(defaultValue = "5") String sortCriterion
    ) {
        CategoryProductIdListDto categoryProductIdListDto = productService.productIdList(largeId,middleId,smallId,detailId,sortCriterion);
        if (categoryProductIdListDto != null) {
            return ResponseEntity.ok(categoryProductIdListDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "리스트용 상품 정보", description = "상품 ID로 상품정보", tags = { "Get ProductInformation" })
    @GetMapping("/productList/{productId}")
    public ResponseEntity<ProductDto> ProductInformation(Long productId) {
        ProductDto productDto = productService.productInformation(productId);
        if (productDto != null) {
            return ResponseEntity.ok(productDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}