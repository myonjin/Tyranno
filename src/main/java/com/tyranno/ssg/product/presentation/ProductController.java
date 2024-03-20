package com.tyranno.ssg.product.presentation;

import com.tyranno.ssg.product.application.ProductService;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.dto.ProductDetailDto;
import com.tyranno.ssg.product.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "상품 상세페이지", description = "상품 상세페이지를 열기", tags = { "Get ProductDetail" })
    @GetMapping("/detail/{productId}")
    public ResponseEntity<ProductDetailDto> productDetail(@PathVariable Long productId) {
        ProductDetailDto productDto = productService.productDetail(productId);

        if (productDto != null) {
            return ResponseEntity.ok(productDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "상품 리스트", description = "상품 리스트를 받아오기", tags = { "Get ProductList" })
    @GetMapping("/detail/productList")
    public ResponseEntity<ProductDto> productList() {
        List<ProductDto> productDto = productService.productList();

        if (productDto != null) {
            return ResponseEntity.ok(productDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}