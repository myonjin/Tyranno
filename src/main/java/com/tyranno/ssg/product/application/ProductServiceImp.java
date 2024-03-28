package com.tyranno.ssg.product.application;

import com.tyranno.ssg.category.dto.CategoryProductIdListDto;
import com.tyranno.ssg.category.infrastructure.CategoryRepository;
import com.tyranno.ssg.category.infrastructure.CategoryRepositoryImp;
import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.product.domain.Discount;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.domain.ProductThum;
import com.tyranno.ssg.product.dto.*;
import com.tyranno.ssg.product.infrastructure.DiscountRepository;
import com.tyranno.ssg.product.infrastructure.ProductRepository;
import com.tyranno.ssg.product.infrastructure.ProductThumRepository;
import com.tyranno.ssg.vendor.domain.Vendor;
import com.tyranno.ssg.vendor.domain.VendorProduct;
import com.tyranno.ssg.vendor.dto.VendorDto;
import com.tyranno.ssg.vendor.infrastructure.VendorProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService{


    // JPA로 productId를 통해 조회하기
    private final ProductRepository productRepository;
    private final VendorProductRepository vendorProductRepository;
    private final ProductThumRepository productThumRepository;
//    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;
    private final CategoryRepositoryImp categoryRepositoryImp;

    //    private final LikeRepository likeRepository;



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
            VendorProduct vendorProduct = vendorProductRepository.findByProductId(product.getId());
            List<VendorDto> vendorDtos = new ArrayList<>();
            if (vendorProduct != null) {
                VendorDto vendorDto = new VendorDto();
                vendorDto.setVendorId(vendorProduct.getVendor().getId());
                vendorDto.setVendorName(vendorProduct.getVendor().getVendorName());
                vendorDtos.add(vendorDto);
            }
            // ProductDto 생성 및 값 설정
            return ProductDetailDto.builder()
                    .productName(product.getProductName())
                    .price(product.getProductPrice())
                    .productRate(product.getProductRate())
                    .detailContent(product.getDetailContent())
//                    .discount(product.getDiscount()) //다른곳에서 오는거
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
    public CategoryProductIdListDto productIdList(Long largeId, Long middleId, Long smallId, Long detailId, String sortCriterion) {
        Optional<List<Long>> productIds = Optional.ofNullable(categoryRepositoryImp.getProductIdList(largeId, middleId, smallId, detailId, sortCriterion));
        CategoryProductIdListDto categoryProductIdListDto = new CategoryProductIdListDto();

        productIds.ifPresent(categoryProductIdListDto::setProductIds);

        return categoryProductIdListDto;
    }

    @Override
    public ProductDto getProductInformation(Long productId){
        Optional<Product> product = productRepository.findById(productId);
        ProductDto productDto = new ProductDto();
        product.ifPresent(p -> {
            productDto.setProductId(p.getId());
            productDto.setProductName(p.getProductName());
            productDto.setPrice(p.getProductPrice());
            productDto.setProductRate(p.getProductRate());
            productDto.setReviewCount(p.getReviewCount());
        });
        return productDto;
    }

    @Override
    public ProductThumDto getProductThumPriority1(Long productId){
        return productThumRepository.findByProductIdAndPriority(productId, 1)
                .map(ProductThumDto::FromEntity)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCTTHUM));
    }

}