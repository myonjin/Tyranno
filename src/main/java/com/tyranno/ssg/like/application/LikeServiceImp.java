package com.tyranno.ssg.like.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.like.dto.LikeDto;
import com.tyranno.ssg.like.infrastructure.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeServiceImp implements LikeService{
    private final LikeRepository likeRepository;
    @Override
    public LikeDto getLikeByProductIdAndUsersId(Long productId, Long usersId) {
        return likeRepository.findByProductIdAndUsersId(productId, usersId)
                .map(LikeDto::FromEntity)
                .orElse(null);
    }
}
