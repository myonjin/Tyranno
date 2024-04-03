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
    public DeliveryDetailDto getDetailDelivery(Long deliveryId) {
        Delivery delivery = getDelivery(deliveryId);
        return DeliveryDetailDto.fromEntity(delivery);
    }

    @Override
    public void modifyDelivery(DeliveryModifyDto deliveryModifyDto) {
        Delivery delivery = getDelivery(deliveryModifyDto.getId());
        deliveryRepository.save(deliveryModifyDto.toEntity(delivery));
    }

    @Override
    public void modifyBaseDelivery(BaseDeliveryModifyDto baseDeliveryModifyDto) {
        Delivery newBasedelivery = getDelivery(baseDeliveryModifyDto.getDeliveryId());
        // 기존 기본배송지 취소
        Delivery Basedelivery = deliveryRepository.findByIsBaseDeliveryAndUsers((byte) 11, newBasedelivery.getUsers())
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_DELIVERY));
        deliveryRepository.save(baseDeliveryModifyDto.toEntity(Basedelivery, (byte) 99));
        // 새 기본배송지 설정
        deliveryRepository.save(baseDeliveryModifyDto.toEntity(newBasedelivery, (byte) 11));
    }

    public Delivery getDelivery(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_DELIVERY));
    }

    @Override
    public String getBaseDeliveryName(String uuid) {
        Users users = getUsers(uuid);
        Delivery delivery = deliveryRepository.findByUsersAndIsBaseDelivery(users, (byte) 11);
        return delivery.getDeliveryName();
    }

    public Users getUsers(String uuid) {
        return usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
    }
}
