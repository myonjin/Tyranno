package com.tyranno.ssg.like.application;

<<<<<<< HEAD
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
=======
import com.tyranno.ssg.delivery.infrastructure.DeliveryRepository;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImp implements LikeService {

    private final UsersRepository usersRepository;
    private final DeliveryRepository deliveryRepository;


>>>>>>> cb5a1269a42d364d47354014b302c03e3bee4d94
}
