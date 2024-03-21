package com.tyranno.ssg.product.application;

import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.domain.ProductThum;
import com.tyranno.ssg.product.dto.ProductDetailDto;
import com.tyranno.ssg.product.dto.ProductDto;
import com.tyranno.ssg.product.dto.ProductListDto;
import com.tyranno.ssg.product.infrastructure.ProductRepository;
import com.tyranno.ssg.product.infrastructure.ProductThumRepository;
import com.tyranno.ssg.vendor.domain.Vendor;
import com.tyranno.ssg.vendor.domain.VendorProduct;
import com.tyranno.ssg.vendor.dto.VendorDto;
import com.tyranno.ssg.vendor.infrastructure.VendorProductRepository;
import com.tyranno.ssg.vendor.infrastructure.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

    // JPA로 productId를 통해 조회하기
    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final VendorProductRepository vendorProductRepository;
    private final ProductThumRepository productThumRepository;


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
    public ProductListDto productList(int sortCriterion, Long categoryLarge, Long categoryMiddle, Long categorySmall, Long categoryDetail) {
        List<Product> productList;

        // 카테고리 필터링을 위한 조건 설정
        if (categoryDetail != null) {
            productList = productRepository.findByCategoryDetailId(categoryDetail);
        } else if (categorySmall != null) {
            productList = productRepository.findByCategorySmallId(categorySmall);
        } else if (categoryMiddle != null) {
            productList = productRepository.findByCategoryMiddleId(categoryMiddle);
        } else if (categoryLarge != null) {
            productList = productRepository.findByCategoryLargeId(categoryLarge);
        } else {
            productList = productRepository.findAll();
        }

        // 정렬 기준에 따라 정렬
        // 여기서는 각각의 정렬 기준에 따라 조건문을 추가하여 처리해야 합니다.
        // 이 예제에서는 정렬 기준에 따라 직접적으로 정렬하는 코드는 제공되지 않았으므로,
        // 해당 부분은 정확한 구현 방법을 알려주셔야 합니다.

        // ProductListDto 객체 생성 및 반환
        ProductListDto productListDto = new ProductListDto();
        List<ProductDto> productDtoList = productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
        productListDto.setProductDtoList(productDtoList);
        return productListDto;
    }

    // Product를 ProductDto로 매핑하는 메서드
    private ProductDto mapToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        // ProductDto에 제품 정보 매핑
        // 예시: productDto.setName(product.getName());
        return productDto;
    }
}