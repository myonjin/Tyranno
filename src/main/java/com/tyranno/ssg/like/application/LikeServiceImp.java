package com.tyranno.ssg.like.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.like.dto.LikeDto;
import com.tyranno.ssg.like.infrastructure.LikeRepository;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LikeServiceImp implements LikeService{
    private final LikeRepository likeRepository;
    private final UsersRepository usersRepository;
    @Override
    public LikeDto getLikeByProductIdAndUsersId(Long productId, String uuid) {

        Long usersId =  usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS)).getId();
        log.info("productId {}",productId);
        log.info("usersId {}", usersId);
        return likeRepository.findByProductIdAndUsersId(productId, usersId)
                .map(LikeDto::FromEntity)
                .orElse(new LikeDto());
    }
}
