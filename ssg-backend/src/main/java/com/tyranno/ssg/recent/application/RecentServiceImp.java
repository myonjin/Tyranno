package com.tyranno.ssg.recent.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.infrastructure.ProductRepository;
import com.tyranno.ssg.recent.domain.RecentViewedProduct;
import com.tyranno.ssg.recent.dto.Response.RecentViewedDto;
import com.tyranno.ssg.recent.infrastructure.RecentViewedRepository;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecentServiceImp implements RecentService {
    private final RecentViewedRepository recentRepository;
    private final UsersRepository usersRepository;
    private final ProductRepository productRepository;

    @Override
    public List<RecentViewedDto> getRecentByUser(String uuid) {
        Byte view = 11;
        Long userId = usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS)).getId();

        List<RecentViewedDto> recentViewedDtos = recentRepository
                .findAllByUsersIdAndIsViewOrderByCreatedAtDesc(userId, view)
                .stream()
                .map(RecentViewedDto::FromEntity)
                .collect(Collectors.toList());
        return recentViewedDtos;
    }

    @Transactional
    @Override
    public String addRecentByProduct(Long productId, String uuid) {
        Users users = usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCT));
        if (recentRepository.existsByProductIdAndUsersAndIsView(productId, users, 11)) {
            RecentViewedProduct pastRecentViewedProduct =
                    recentRepository.findByProductIdAndUsersAndIsView(productId, users, 11);

            pastRecentViewedProduct = RecentViewedProduct.builder()
                    .id(pastRecentViewedProduct.getId())
                    .product(pastRecentViewedProduct.getProduct())
                    .users(pastRecentViewedProduct.getUsers())
                    .isView((byte) 99)
                    .build();
            recentRepository.save(pastRecentViewedProduct);

            RecentViewedProduct newRecentViewedProduct = RecentViewedProduct.builder()
                    .product(pastRecentViewedProduct.getProduct())
                    .users(pastRecentViewedProduct.getUsers())
                    .isView((byte) 11) // 새로운 정보의 isView 값을 11로 설정
                    .build();
            recentRepository.save(newRecentViewedProduct);
            return "최근 본 상품 재추가!";
        }
            RecentViewedProduct recentViewedProduct = RecentViewedProduct.builder()
                    .product(product)
                    .users(users)
                    .isView((byte) 11)
                    .build();

            recentRepository.save(recentViewedProduct);
        return "최근 본 상품 추가!";
    }
    @Transactional
    @Override
    public String deleteRecentByProduct(Long productId, String uuid) {
        Users users = usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCT));
        RecentViewedProduct recentViewedProduct =
                recentRepository.findByProductIdAndUsersAndIsView(productId, users, 11);
        recentViewedProduct = RecentViewedProduct.builder()
                .id(recentViewedProduct.getId())
                .product(recentViewedProduct.getProduct())
                .users(recentViewedProduct.getUsers())
                .isView((byte) 99)
                .build();
        recentRepository.save(recentViewedProduct);
        return "최근 본 상품 삭제";
    }
}



