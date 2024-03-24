package com.tyranno.ssg.product.presentation;

import com.tyranno.ssg.category.application.CategoryService;
import com.tyranno.ssg.product.application.ProductService;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.dto.ProductDetailDto;
import com.tyranno.ssg.product.dto.ProductDto;
import com.tyranno.ssg.product.dto.ProductListDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Operation(summary = "상품 상세페이지", description = "상품 상세페이지를 열기", tags = { "Get ProductDetail" })
    @GetMapping("/detail/{productId}")
    public ResponseEntity<ProductDetailDto> productDetail(@PathVariable Long productId) {
        ProductDetailDto productDetailDto = productService.productDetail(productId);
        if (productDetailDto != null) {
            return ResponseEntity.ok(productDetailDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "상품 리스트", description = "상품 리스트를 받아오기", tags = { "Get ProductList" })
    @GetMapping("/productList")
    public ResponseEntity<List<ProductDto>> getProductList(
            @RequestParam(required = false) Long largeId,
            @RequestParam(required = false) Long middleId,
            @RequestParam(required = false) Long smallId,
            @RequestParam(required = false) Long detailId,
            @RequestParam(defaultValue = "5") String sortCriterion
    ) {
        List<ProductDto> productDtoList;

        if (largeId != null && middleId == null && smallId == null && detailId == null) {
            // largeId만 존재하는 경우
            List<Long> productIds = categoryService.getProductIdsByLargeId(largeId);
            productDtoList = productService.getProductList(productIds);
        } else if (largeId == null && middleId != null && smallId == null && detailId == null) {
            // middleId만 존재하는 경우
            List<Long> productIds = categoryService.getProductIdsByMiddleId(middleId);
            productDtoList = productService.getProductList(productIds);
        } else if (largeId == null && middleId == null && smallId != null && detailId == null) {
            // smallId만 존재하는 경우
            List<Long> productIds = categoryService.getProductIdsBySmallId(smallId);
            productDtoList = productService.getProductList(productIds);
        } else if (largeId == null && middleId == null && smallId == null && detailId != null) {
            // detailId만 존재하는 경우
            List<Long> productIds = categoryService.getProductIdsByDetailId(detailId);
            productDtoList = productService.getProductList(productIds);
        } else {
            // 나머지 경우
            return ResponseEntity.badRequest().build();
        }

        if (!productDtoList.isEmpty()) {
            return ResponseEntity.ok(productDtoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}