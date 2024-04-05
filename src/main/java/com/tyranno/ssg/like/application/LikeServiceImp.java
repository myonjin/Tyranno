package com.tyranno.ssg.like.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.like.domain.Like;
import com.tyranno.ssg.like.dto.LikeDto;
import com.tyranno.ssg.like.infrastructure.LikeRepository;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.infrastructure.ProductRepository;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LikeServiceImp implements LikeService{
    private final LikeRepository likeRepository;
    private final UsersRepository usersRepository;
    private final ProductRepository productRepository;
    @Override
    public LikeDto getLikeByProductIdAndUsersId(Long productId, String uuid) {

        Long usersId =  usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS)).getId();
        return likeRepository.findByProductIdAndUsersId(productId, usersId)
                .map(LikeDto::FromEntity)
                .orElse(new LikeDto());
    }
    @Override
    @Transactional
    public boolean modifyLike(Long productId, String uuid) {
        Users users =  usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCT));
        Optional<Like> like = likeRepository.findByProductIdAndUsersId(productId, users.getId()); // 상품을 좋아요 했는지 확인
        if (like.isPresent()) {// 이미 좋아요한 경우 삭제
            likeRepository.deleteByProductIdAndUsersId(productId, users.getId());
            return false;
        } else {// 좋아요 하지 않은 경우 추가
            Like newLike = Like.builder()
                    .product(product)
                    .users(users)
                            .build();
            likeRepository.save(newLike);
            return true; // 찜이 추가되었음을 나타냄
        }
    }
}
