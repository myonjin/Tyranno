package com.tyranno.ssg.product.application;

import com.tyranno.ssg.category.infrastructure.CategoryRepository;
import com.tyranno.ssg.category.infrastructure.CategoryRepositoryImp;
import com.tyranno.ssg.product.domain.Discount;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.domain.ProductThum;
import com.tyranno.ssg.product.dto.DiscountDto;
import com.tyranno.ssg.product.dto.ProductDetailDto;
import com.tyranno.ssg.product.dto.ProductDto;
import com.tyranno.ssg.product.dto.ProductListDto;
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

    // Discount 객체 구하기
    @Override
    public DiscountDto findDiscountByProductId(Long id){
        Discount discount = discountRepository.findByProductId(id);
        return DiscountDto.builder()
                .build();
    }



    @Override
    public List<ProductDto> getProductList(Long largeId, Long middleId, Long smallId, Long detailId, String sortCriterion) {
        List<Product> productList = categoryRepositoryImp.getProductList(largeId, middleId, smallId, detailId, sortCriterion);
        List<ProductDto> productDtos = new ArrayList<>();

        if (productList != null) {
            for (Product product : productList) {
                log.info("product : {}",product);
                String vendorName = vendorProductRepository.findByProductId(product.getId()).getVendor().getVendorName();
                String imageUrl = productThumRepository.findByProductIdAndPriority(product.getId(),1).getImageUrl();

                ProductDto productDto = new ProductDto();
                productDto.setProductId(product.getId());
                productDto.setProductName(product.getProductName());
                productDto.setPrice(product.getProductPrice());
                productDto.setProductRate(product.getProductRate());
                productDto.setReviewCount(product.getReviewCount());
                productDto.setVendorName(vendorName);
                productDto.setImageUrl(imageUrl);
//                ProductDto.builder()
//                        .productId(productDto.getProductId())
//                        .productName(productDto.getProductName())
//                        .price(product.getProductPrice())
//                        .productRate(product.getProductRate())
//                        .reviewCount(product.getReviewCount()).vendorName(product.)

                productDtos.add(productDto);
            }
        }

        return productDtos;
    }


    @Override
    public List<ProductDto> getProductListSorted(List<ProductDto> beforeProductDtoList, String sortCriterion) {
        // productIds를 이용하여 제품 정보 조회
        List<ProductDto> productDtos = new ArrayList<>(beforeProductDtoList);

        // 정렬 로직 추가
        if ("1".equals(sortCriterion)) {
            // 낮은 가격 순
            Collections.sort(productDtos, Comparator.comparing(ProductDto::getPrice));
            log.info("정렬방법: 낮은 가격순 ");
        } else if ("2".equals(sortCriterion)) {
            // 높은 가격 순
            Collections.sort(productDtos, Comparator.comparing(ProductDto::getPrice).reversed());
            log.info("정렬방법: 높은 가격순");
        } else if ("3".equals(sortCriterion)) {
            // 할인율 높은 순
            Collections.sort(productDtos, Comparator.comparing(ProductDto::getDiscount).reversed());
            log.info("정렬방법: 할인율 높은 순");
        } else if ("4".equals(sortCriterion)) {
            // 리뷰 많은 순
            Collections.sort(productDtos, Comparator.comparing(ProductDto::getReviewCount).reversed());
            log.info("정렬방법: 리뷰 많은 순");
        } else {
            Collections.sort(productDtos, Comparator.comparing(ProductDto::getProductId));
            log.info("정렬방법: 기본(아이디 순)");
        }
        // 기타 필요한 정렬 조건 추가

        return productDtos;
    }


}