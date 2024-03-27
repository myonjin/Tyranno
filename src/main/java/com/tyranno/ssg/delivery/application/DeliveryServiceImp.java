package com.tyranno.ssg.delivery.application;

import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.delivery.dto.*;
import com.tyranno.ssg.delivery.infrastructure.DeliveryRepository;
import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImp implements DeliveryService {

    private final UsersRepository usersRepository;
    private final DeliveryRepository deliveryRepository;

    @Override
    public void addDelivery(DeliveryAddDto deliveryAddDto, String uuid) {

        deliveryRepository.save(deliveryAddDto.toEntity(getUsers(uuid)));
    }

    @Override
    public void deleteDelivery(Long deliveryId) {

        deliveryRepository.deleteById(deliveryId);
    }

    @Override
    public List<DeliveryListDto> getDeliveryList(String uuid) {

        List<Delivery> deliveries = deliveryRepository.findByUsersUuid(uuid);

        return deliveries.stream()
                .map(DeliveryListDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SingleDeliveryDto getSingleDelivery(Long deliveryId) {
        Delivery delivery = getDelivery(deliveryId);
        return SingleDeliveryDto.fromEntity(delivery);
    }

    @Override
    public void modifyDelivery(DeliveryModifyDto deliveryModifyDto) {
        Delivery delivery = getDelivery(deliveryModifyDto.getId());
        deliveryRepository.save(deliveryModifyDto.toEntity(delivery));
    }

    @Override
    public void modifyBaseDelivery(BaseDeliveryModifyDto baseDeliveryModifyDto) {
        // 기존 기본배송지 취소
        Delivery delivery = deliveryRepository.findByIsBaseDelivery((byte) 11)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_DELIVERY));
        deliveryRepository.save(baseDeliveryModifyDto.toEntity(delivery, (byte) 99));
        // 새 기본배송지 설정
        delivery = getDelivery(baseDeliveryModifyDto.getId());
        deliveryRepository.save(baseDeliveryModifyDto.toEntity(delivery, (byte) 11));
    }

    public Delivery getDelivery(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_DELIVERY));
    }

    public Users getUsers(String uuid) {
        return usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
    }
}
