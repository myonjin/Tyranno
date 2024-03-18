package com.tyranno.ssg.product.presentation;

import com.tyranno.ssg.product.application.ProductService;
import com.tyranno.ssg.product.domain.Product;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "상품 상세페이지", description = "상품 상세페이지를 열기", tags = { "Get ProductDetail" })
    @GetMapping("/detail/{productId}}")
    public Product productDetail(@PathVariable Long productId){
        return null;
    }

}
