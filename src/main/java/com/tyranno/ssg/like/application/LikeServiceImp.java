package com.tyranno.ssg.like.application;

import com.tyranno.ssg.delivery.infrastructure.DeliveryRepository;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImp implements LikeService {

    private final UsersRepository usersRepository;
    private final DeliveryRepository deliveryRepository;


}
