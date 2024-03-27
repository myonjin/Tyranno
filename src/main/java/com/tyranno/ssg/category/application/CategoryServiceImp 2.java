package com.tyranno.ssg.category.application;

import com.tyranno.ssg.category.domain.CategoryDetail;
import com.tyranno.ssg.category.domain.CategoryLarge;
import com.tyranno.ssg.category.domain.CategoryMiddle;
import com.tyranno.ssg.category.domain.CategorySmall;
import com.tyranno.ssg.category.dto.DetailCategoryDto;
import com.tyranno.ssg.category.dto.LargeCategoryDto;
import com.tyranno.ssg.category.dto.MiddleCategoryDto;
import com.tyranno.ssg.category.dto.SmallCategoryDto;
import com.tyranno.ssg.category.infrastructure.*;
import com.tyranno.ssg.product.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImp implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryLargeRepository categoryLargeRepository;
    private final CategoryMiddleRepository categoryMiddleRepository;
    private final CategorySmallRepository categorySmallRepository;
    private final CategoryDetailRepository categoryDetailRepository;

    @Override
    public List<Long> getProductIdsByLargeId(Long largeId) {
        // CategoryRepository를 사용하여 largeId를 기준으로 Product 객체들을 찾습니다.
        List<Product> products = categoryRepository.findProductsByLargeId(largeId);

        // Product 객체들의 id를 저장할 리스트를 생성합니다.
        List<Long> productIds = new ArrayList<>();

        // Product 객체들을 순회하면서 각 Product의 id를 추출하여 리스트에 추가합니다.
        for (Product product : products) {
            productIds.add(product.getId());
        }

        // 결과로 얻은 Product 객체들의 id 리스트를 반환합니다.
        return productIds;
    }

    @Override
    public List<Long> getProductIdsByMiddleId(Long middleId) {
        // CategoryRepository를 사용하여 largeId를 기준으로 Product 객체들을 찾습니다.
        List<Product> products = categoryRepository.findProductsByMiddleId(middleId);

        // Product 객체들의 id를 저장할 리스트를 생성합니다.
        List<Long> productIds = new ArrayList<>();

        // Product 객체들을 순회하면서 각 Product의 id를 추출하여 리스트에 추가합니다.
        for (Product product : products) {
            productIds.add(product.getId());
        }

        // 결과로 얻은 Product 객체들의 id 리스트를 반환합니다.
        return productIds;
    }

    @Override
    public List<Long> getProductIdsBySmallId(Long smallId) {
        // CategoryRepository를 사용하여 largeId를 기준으로 Product 객체들을 찾습니다.
        List<Product> products = categoryRepository.findProductsBySmallId(smallId);

        // Product 객체들의 id를 저장할 리스트를 생성합니다.
        List<Long> productIds = new ArrayList<>();

        // Product 객체들을 순회하면서 각 Product의 id를 추출하여 리스트에 추가합니다.
        for (Product product : products) {
            productIds.add(product.getId());
        }

        // 결과로 얻은 Product 객체들의 id 리스트를 반환합니다.
        return productIds;
    }

    @Override
    public List<Long> getProductIdsByDetailId(Long detailId) {
        // CategoryRepository를 사용하여 largeId를 기준으로 Product 객체들을 찾습니다.
        List<Product> products = categoryRepository.findProductsByDetailId(detailId);

        // Product 객체들의 id를 저장할 리스트를 생성합니다.
        List<Long> productIds = new ArrayList<>();

        // Product 객체들을 순회하면서 각 Product의 id를 추출하여 리스트에 추가합니다.
        for (Product product : products) {
            productIds.add(product.getId());
        }

        // 결과로 얻은 Product 객체들의 id 리스트를 반환합니다.
        return productIds;
    }

    @Override
    public List<LargeCategoryDto> getLargeCategory(){ // largeCategory 조회
        List<CategoryLarge> LargeCategories = categoryLargeRepository.findAll();
        List<LargeCategoryDto> larges = new ArrayList<>();

        for (CategoryLarge largeCategory : LargeCategories) {
            LargeCategoryDto dto = LargeCategoryDto.builder()
                    .largeId(largeCategory.getId())
                    .largeName(largeCategory.getLargeName())
                    .largeImageUrl(largeCategory.getLargeImageUrl())
                    .build();
            larges.add(dto);
        }
        return larges;
    }

    @Override
    public List<MiddleCategoryDto> getMiddleCategory(Long largeId){ // middleCategory 조회
        List<CategoryMiddle> MiddleCategories = categoryMiddleRepository.findByCategoryLargeId(largeId);
        List<MiddleCategoryDto> middles = new ArrayList<>();

        for (CategoryMiddle middleCategory : MiddleCategories) {
            MiddleCategoryDto dto = MiddleCategoryDto.builder()
                    .middleId(middleCategory.getId())
                    .middleName(middleCategory.getMiddleName())
                    .middleImageUrl(middleCategory.getMiddleImageUrl())
                    .build();
            middles.add(dto);
        }
        return middles;
    }

    @Override
    public List<SmallCategoryDto> getSmallCategory(Long middleId){ // smallCategory 조회
        List<CategorySmall> SmallCategories = categorySmallRepository.findByCategoryMiddleId(middleId);
        List<SmallCategoryDto> smalls = new ArrayList<>();

        for (CategorySmall smallCategory : SmallCategories) {
            SmallCategoryDto dto = SmallCategoryDto.builder()
                    .smallId(smallCategory.getId())
                    .smallName(smallCategory.getSmallName())
                    .build();
            smalls.add(dto);
        }
        return smalls;
    }

    @Override
    public List<DetailCategoryDto> getDetailCategory(Long smallId){ // detailCategory 조회
        List<CategoryDetail> DetailCategories = categoryDetailRepository.findByCategorySmallId(smallId);
        List<DetailCategoryDto> details = new ArrayList<>();

        for (CategoryDetail detailCategory : DetailCategories) {
            DetailCategoryDto dto = DetailCategoryDto.builder()
                    .detailId(detailCategory.getId())
                    .detailName(detailCategory.getDetailName())
                    .build();
            details.add(dto);
        }
        return details;
    }
}
