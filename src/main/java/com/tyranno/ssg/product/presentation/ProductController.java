package com.tyranno.ssg.product.presentation;

import com.tyranno.ssg.category.application.CategoryService;
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

    @Operation(summary = "상품 리스트", description = "상품 리스트를 받아오기", tags = { "Get ProductList" })
    @GetMapping("/productList")
    public ResponseEntity<List<ProductDto>> getProductList(
            @RequestParam(required = false) Long largeId,
            @RequestParam(required = false) Long middleId,
            @RequestParam(required = false) Long smallId,
            @RequestParam(required = false) Long detailId,
            @RequestParam(defaultValue = "5") String sortCriterion
    ) {
        // 각각의 경우에 대한 로그
        log.debug("파라미터 - largeId: {}, middleId: {}, smallId: {}, detailId: {}, sortCriterion: {}", largeId, middleId, smallId, detailId, sortCriterion);

        List<ProductDto> productDtoList;
        List<ProductDto> beforeProductDtoList;

        if (largeId != null && middleId == null && smallId == null && detailId == null) {
            // largeId만 존재하는 경우
            log.info("largeId: {}", largeId);
            List<Long> productIds = categoryService.getProductIdsByLargeId(largeId);
            beforeProductDtoList = productService.getProductList(productIds);
        } else if (largeId == null && middleId != null && smallId == null && detailId == null) {
            // middleId만 존재하는 경우
            log.info("middleId: {}", middleId);
            List<Long> productIds = categoryService.getProductIdsByMiddleId(middleId);
            beforeProductDtoList = productService.getProductList(productIds);
        } else if (largeId == null && middleId == null && smallId != null && detailId == null) {
            // smallId만 존재하는 경우
            log.info("smallId: {}", smallId);
            List<Long> productIds = categoryService.getProductIdsBySmallId(smallId);
            beforeProductDtoList = productService.getProductList(productIds);
        } else if (largeId == null && middleId == null && smallId == null && detailId != null) {
            // detailId만 존재하는 경우
            log.info("detailId: {}", detailId);
            List<Long> productIds = categoryService.getProductIdsByDetailId(detailId);
            beforeProductDtoList = productService.getProductList(productIds);
        } else {
            // 나머지 경우
            log.error("없다 이놈아");
            return ResponseEntity.badRequest().build();
        }
        productDtoList = productService.getProductListSorted(beforeProductDtoList, sortCriterion);

        if (!productDtoList.isEmpty()) {
            return ResponseEntity.ok(productDtoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}