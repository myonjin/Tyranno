package com.tyranno.ssg.product.presentation;

import com.tyranno.ssg.category.application.CategoryService;
import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.product.application.ProductService;
import com.tyranno.ssg.product.dto.ProductDetailDto;
import com.tyranno.ssg.product.dto.ProductIdListDto;
import com.tyranno.ssg.product.dto.ProductInformationDto;
import com.tyranno.ssg.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "상품", description = "상품 API")
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "상품 상세페이지", description = "상품 상세페이지를 열기")
    @GetMapping("/detail/{productId}")
    public ResponseEntity<ProductDetailDto> productDetail(@PathVariable Long productId) {
        log.info("productDetail 실행");
        log.info(productId.toString());

        ProductDetailDto productDetailDto = productService.productDetail(productId);

        return new ResponseEntity<>(productDetailDto);
    }

    @Operation(summary = "리스트용 상품 정보", description = "상품 ID로 상품정보")
    @GetMapping("/productInformation/{productId}")
    public ResponseEntity<?> ProductInformation(@PathVariable Long productId,
                                                @RequestHeader(value = "Authorization", required = false) String token) {
        if(token != null) {
            String uuid = jwtTokenProvider.tokenToUuid(token);
            ProductInformationDto productDto = productService.getProductInformation(productId,uuid);
            log.info(String.valueOf(productDto));
            return new ResponseEntity<>(productDto);
        }
        else {
            String uuid = null;
            ProductInformationDto productDto = productService.getProductInformation(productId, null);
            log.info(String.valueOf(productDto));
            return new ResponseEntity<>(productDto);
        }
    }

    @Operation(summary = "상품 ID 리스트", description = "상품 ID 리스트를 받아오기")
    @GetMapping("/productList")
    public ResponseEntity<?> getProductIdList(
            @RequestParam(required = false) Long largeId,
            @RequestParam(required = false) Long middleId,
            @RequestParam(required = false) Long smallId,
            @RequestParam(required = false) Long detailId,
            @RequestParam(defaultValue = "5") Integer sortCriterion,
            @RequestParam(defaultValue = "0") Integer lastIndex,
            @RequestParam(required = false) String searchKeyword
    ) {
        ProductIdListDto productIdListDto = productService.getProductIdList(largeId, middleId, smallId,
                detailId, sortCriterion, lastIndex, searchKeyword);
        return new com.tyranno.ssg.global.ResponseEntity<>(productIdListDto);
    }

}