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
    @GetMapping("/detail/{product_id}")
    public ResponseEntity<ProductDetailDto> productDetail(@PathVariable Long product_id) {
        log.info("productDetail 실행");
        log.info(product_id.toString());

        ProductDetailDto productDetailDto = productService.productDetail(product_id);

        return new ResponseEntity<>(productDetailDto);
    }

    @Operation(summary = "리스트용 상품 정보", description = "상품 ID로 상품정보" +
            "isLike 포함, image 한개만 포함")
    @GetMapping("/productInformation/{product_id}")
    public ResponseEntity<?> ProductInformation(@PathVariable Long product_id,
                                                @RequestHeader(value = "Authorization", required = false) String token) {
        if(token != null) {
            String uuid = jwtTokenProvider.tokenToUuid(token);
            ProductInformationDto productDto = productService.getProductInformation(product_id,uuid);
            log.info(String.valueOf(productDto));
            return new ResponseEntity<>(productDto);
        }
        else {
            String uuid = null;
            ProductInformationDto productDto = productService.getProductInformation(product_id, null);
            log.info(String.valueOf(productDto));
            return new ResponseEntity<>(productDto);
        }
    }

    @Operation(summary = "상품 ID 리스트", description = "상품 ID 리스트를 받아오기,\n\n" +
            "sortCriterion 1: 낮은 가격순,\n\n" +
            "sortCriterion 2: 높은 가격순,\n\n" +
            "sortCriterion 3: 평점 높은 순,\n\n" +
            "sortCriterion 4: 리뷰 많은 순,\n\n" +
            "sortCriterion 5: 상품 아이디 순(기본값),\n\n" +
            "카테고리는 필요한 것만 1개 넣기,\n\n" +
            "page는 무한 스크롤을 위해 1부터 시작 (1,2,3,4)이런식\n\n" +
            "검색어는 카테고리 값 넣지 않고 넣어야 함")
    @GetMapping("/productList")
    public ResponseEntity<?> getProductIdList(
            @RequestParam(required = false) Long largeId,
            @RequestParam(required = false) Long middleId,
            @RequestParam(required = false) Long smallId,
            @RequestParam(required = false) Long detailId,
            @RequestParam(defaultValue = "5") Integer sortCriterion,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        ProductIdListDto productIdListDto = productService.getProductIdList(largeId, middleId, smallId,
                detailId, sortCriterion, page);
        return new ResponseEntity<>(productIdListDto);
    }
}