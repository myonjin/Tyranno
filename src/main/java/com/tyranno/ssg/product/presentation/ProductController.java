package com.tyranno.ssg.product.presentation;

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
            @RequestParam String sortCriterion
    ) {
        List<ProductDto> productDtoList = productService.getProductDtoList(largeId, middleId, smallId, detailId, sortCriterion);

        if (!productDtoList.isEmpty()) {
            return ResponseEntity.ok(productDtoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}