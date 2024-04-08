package com.tyranno.ssg.product.application;


import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.like.domain.Like;
import com.tyranno.ssg.like.infrastructure.LikeRepository;
import com.tyranno.ssg.product.domain.Discount;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.domain.ProductThum;
import com.tyranno.ssg.product.dto.*;
import com.tyranno.ssg.product.infrastructure.DiscountRepository;
import com.tyranno.ssg.product.infrastructure.ProductRepository;
import com.tyranno.ssg.product.infrastructure.ProductRepositoryImp;
import com.tyranno.ssg.product.infrastructure.ProductThumRepository;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import com.tyranno.ssg.vendor.domain.Vendor;
import com.tyranno.ssg.vendor.domain.VendorProduct;
import com.tyranno.ssg.vendor.dto.VendorDto;
import com.tyranno.ssg.vendor.infrastructure.VendorProductRepository;
import com.tyranno.ssg.vendor.infrastructure.VendorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {


    private static final int PAGE_SIZE = 20;
    // JPA로 productId를 통해 조회하기
    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final VendorProductRepository vendorProductRepository;
    private final ProductThumRepository productThumRepository;
    //    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;
    private final ProductRepositoryImp productRepositoryImp;
    private final UsersRepository usersRepository;
    private final LikeRepository likeRepository;


    @Override
    public ProductDetailDto productDetail(@PathVariable("productId") Long id) {
        Optional<Product> Product = productRepository.findById(id);

        // 상품이 존재하는지 확인
        if (Product.isPresent()) {
            // 썸네일 이미지 리스트 형식으로 담기위해 불러오기
            Product product = Product.get();
            List<ProductThum> productThums = productThumRepository.findAllByProductId(product.getId());
            List<String> imageUrls = productThums.stream()
                    .map(ProductThum::getImageUrl)
                    .toList();
            // vendor 담기
            // 상품 아이디로 판매자-상품 중간테이블에서 조회
            Optional<VendorProduct> vendorProduct = vendorProductRepository.findByProductId(product.getId());
            List<VendorDto> vendorDtos = vendorProduct
                    .map(vp -> {
                        VendorDto vendorDto = new VendorDto();
                        vendorDto.setVendorId(vp.getVendor().getId());
                        vendorDto.setVendorName(vp.getVendor().getVendorName());
                        return vendorDto;
                    })
                    .map(Collections::singletonList) //GPT 사용
                    .orElse(Collections.emptyList());
            Optional<Discount> discountOptional = discountRepository.findByProductId(product.getId());
            int discountValue = 0;
            if (discountOptional.isPresent()) {
               discountValue = discountOptional.get().getDiscount();
            }

            // ProductDto 생성 및 값 설정
            return ProductDetailDto.builder()
                    .productName(product.getProductName())
                    .price(product.getProductPrice())
                    .productRate(product.getProductRate())
                    .detailContent(product.getDetailContent())
                    .discount(discountValue) //다른곳에서 오는거
                    .reviewCount(product.getReviewCount())
                    .vendor(vendorDtos) // 다른곳에서 오는거
                    .imageUrl(imageUrls) // 다른곳에서 오는거
                    .build();
        } else {
            // 상품이 존재하지 않으면 null 반환
            return null;
        }
    }

    @Override
    public ProductInformationDto getProductInformation(Long productId, String uuid) {// productList에 출력할 상품내용 불러오기
        Optional<Product> productOptional = productRepository.findById(productId);

        Product product = productOptional.orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCT));
        log.info(String.valueOf(product));
        Optional<ProductThum> imageUrl = productThumRepository.findByProductIdAndPriority(productId, 1);
        Long vendorId = null;
        String vendorName = null;

        Optional<VendorProduct> vendorProductOptional = vendorProductRepository.findByProductId(productId);
        if (vendorProductOptional.isPresent()) {
            Vendor vendor = vendorProductOptional.get().getVendor();
            if (vendor != null) {
                vendorId = vendor.getId();
                vendorName = vendor.getVendorName();
            }
        }

        Optional<Discount> discountOptional = discountRepository.findByProductId(product.getId());
        int discountValue = 0;
        if (discountOptional.isPresent()) {
            discountValue = discountOptional.get().getDiscount();
        }
        byte isLike = 99; // 기본값으로 설정

        if (uuid != null) {
            Long usersId = getUsers(uuid).getId();
            Optional<Like> like = likeRepository.findByProductIdAndUsersId(productId, usersId);
            isLike = (byte) (like.isPresent() ? 11 : 99);
        }

        return ProductInformationDto.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .price(product.getProductPrice())
                .productRate(product.getProductRate())
                .reviewCount(product.getReviewCount())
                .isLiked(isLike)
                .imageUrl(imageUrl.orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_IMAGE)).getImageUrl())
                .vendorName(vendorName)
                .discount(discountValue)
                .build();
    }
    @Override
    public ProductThumDto getProductThumPriority1(Long productId){ // productList에서 상품 썸네일 불러오기
        return productThumRepository.findByProductIdAndPriority(productId, 1)
                .map(ProductThumDto::FromEntity)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCTTHUM));
    }
    @Override
    public ProductIdListDto getProductIdList(Long largeId, Long middleId, Long smallId, Long detailId,
                                             Integer sortCriterion, Integer page, String searchKeyword) { // productList
        // 페이지당 항목 수 상수 선언
        final int PAGE_SIZE = 20; // 예시로 20개로 설정

        List<Long> productIds;
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            // 상품명으로 검색
            log.info("상품명 실행");
            productIds = productRepositoryImp.searchProductIdsByKeyword(searchKeyword, sortCriterion, page);
        } else {
            // 카테고리로 검색
            log.info("카테고리 실행");
            productIds = productRepositoryImp.searchProductIdsByCategory(largeId, middleId, smallId, detailId,
                    sortCriterion, page);
        }
        ProductIdListDto productIdListDto = new ProductIdListDto();

        List<Map<String, Object>> productIdList = new ArrayList<>();
        int startIndex = (page - 1) * PAGE_SIZE; // 페이지 번호가 1부터 시작하므로 수정
        for (int i = 0; i < productIds.size(); i++) {
            Long productId = productIds.get(i);
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("productId", productId);
            productMap.put("id", startIndex + i + 1); // 인덱스도 1부터 시작하도록 수정
            productIdList.add(productMap);
        }
        productIdListDto.setProductIds(productIdList);

        return productIdListDto;
    }
    public Users getUsers(String uuid) {
        return usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
    }

    @Override
    @Transactional
    public void updateProductRatingAndReviewCount(Long productId, Float rate) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCT));

        // 기존 평점과 리뷰 수
        float currentRate = product.getProductRate();
        int currentReviewCount = product.getReviewCount();

        // 새로운 평점을 계산
        float newRate = (currentRate * currentReviewCount + rate) / (currentReviewCount + 1);

        Product updatedProduct = Product.builder()
                .id(product.getId())
                .productRate(newRate)
                .reviewCount(currentReviewCount + 1)
                .build();

        // 상품을 저장합니다.
        productRepository.save(updatedProduct);
    }
}