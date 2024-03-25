package com.tyranno.ssg.category.application;

import com.tyranno.ssg.category.domain.Category;
import com.tyranno.ssg.category.dto.CategoryDto;
import com.tyranno.ssg.category.infrastructure.CategoryRepository;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.domain.ProductThum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImp implements CategoryService{

    private final CategoryRepository categoryRepository;

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
}
