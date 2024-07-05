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
    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final VendorProductRepository vendorProductRepository;
    private final ProductThumRepository productThumRepository;
    private final DiscountRepository discountRepository;
    private final ProductRepositoryImp productRepositoryImp;
    private final UsersRepository usersRepository;
    private final LikeRepository likeRepository;


    @Override
    public ProductDetailDto productDetail(@PathVariable("productId") Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            List<ProductThum> productThums = productThumRepository.findAllByProductId(product.getId());
            List<String> imageUrls = productThums.stream()
                    .map(ProductThum::getImageUrl)
                    .toList();

            Vendor vendor = null;

            Optional<VendorProduct> vendorProductOptional = vendorProductRepository.findByProductId(id);
            if (vendorProductOptional.isPresent()) {
                vendor = vendorProductOptional.get().getVendor();
            }

            Optional<Discount> discountOptional = discountRepository.findByProductId(product.getId());
            int discountValue = discountOptional.map(Discount::getDiscount).orElse(0);

            VendorDto vendorDto = null;
            if (vendor != null) {
                vendorDto = VendorDto.FromEntity(vendor);
            }

            return ProductDetailDto.builder()
                    .productName(product.getProductName())
                    .price(product.getProductPrice())
                    .productRate(product.getProductRate())
                    .detailContent(product.getDetailContent())
                    .discount(discountValue)
                    .reviewCount(product.getReviewCount())
                    .vendor(vendorDto)
                    .imageUrl(imageUrls)
                    .build();
        } else {
            return null;
        }
    }



    @Override
    public ProductInformationDto getProductInformation(Long productId, String uuid) {// productList에 출력할 상품내용 불러오기
        Optional<Product> productOptional = productRepository.findById(productId);

        Product product = productOptional.orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCT));
        log.info(String.valueOf(product));
        String imageUrl = productThumRepository.findByProductIdAndPriority(productId, 1)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_IMAGE)).getImageUrl();
        Long vendorId = null;
        String vendorName = "";

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
        float productRate = 0;

        if (product.getReviewCount() != 0) {
            productRate = product.getProductRate()/product.getReviewCount();
        }

        return ProductInformationDto.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .price(product.getProductPrice())
                .productRate(productRate)
                .reviewCount(product.getReviewCount())
                .isLiked(isLike)
                .imageUrl(imageUrl)
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
                                             Integer sortCriterion, Integer page) { // productList
        // 페이지당 10개
        final int PAGE_SIZE = 10;

        List<Long> productIds;
        productIds = productRepositoryImp.searchProductIdsByCategory(largeId, middleId, smallId, detailId,
                    sortCriterion, page);

        ProductIdListDto productIdListDto = new ProductIdListDto();

        List<Map<String, Object>> productIdList = new ArrayList<>();
        int startIndex = (page - 1) * PAGE_SIZE; // 페이지 번호가 1부터 시작
        for (int i = 0; i < productIds.size(); i++) {
            Long productId = productIds.get(i);
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("productId", productId);
            productMap.put("id", startIndex + i + 1); // 인덱스 1부터 시작
            productIdList.add(productMap);
        }
        productIdListDto.setProductIds(productIdList);
        List<Long> productCounts = productRepositoryImp.searchAllProductIdsByCategory(largeId, middleId, smallId, detailId, sortCriterion);
        int totalIds = productCounts.size();
        if (totalIds % 10 != 0) {
            int lastPage = (totalIds/10)+1;
            productIdListDto.setLastPage(lastPage);
        } else {
            int lastPage = (totalIds/10);
            productIdListDto.setLastPage(lastPage);
        }
        productIdListDto.setCurrentPage(page);

        productIdListDto.setTotalCount(totalIds);

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

        Product updatedProduct = Product.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productRate(currentRate + rate)
                .reviewCount(currentReviewCount + 1)
                .detailContent(product.getDetailContent())
                .build();

        // 상품을 저장
        productRepository.save(updatedProduct);
    }
}