package com.tyranno.ssg.delivery.application;

import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.delivery.dto.*;
import com.tyranno.ssg.delivery.dto.response.BaseDeliveryInfoDto;
import com.tyranno.ssg.delivery.dto.response.OrderDeliveryInfoDto;
import com.tyranno.ssg.delivery.infrastructure.DeliveryRepository;
import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImp implements DeliveryService {

    private final UsersRepository usersRepository;
    private final DeliveryRepository deliveryRepository;

    @Transactional
    @Override
    public void addDelivery(DeliveryAddDto deliveryAddDto, String uuid) {
        Users users = usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
        deliveryRepository.save(deliveryAddDto.toEntity(users));
    }

    @Transactional
    @Override
    public void deleteDelivery(Long deliveryId) {

        deliveryRepository.deleteById(deliveryId);
    }

    @Override
    public List<DeliveryListDto> getDeliveryList(String uuid) {

        List<Delivery> deliveries = deliveryRepository.findByUsersUuid(uuid);
        // baseDelivery를 리스트의 맨 앞에 추가
        Delivery baseDelivery = getBaseDelivery(uuid);
        deliveries.remove(baseDelivery);
        deliveries.add(0, baseDelivery);

        return deliveries.stream()
                .map(DeliveryListDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public BaseDeliveryInfoDto getBaseDeliveryInfo(String uuid){
        Delivery delivery = getBaseDelivery(uuid);
        return BaseDeliveryInfoDto.fromEntity(delivery);
    }
    @Override
    public OrderDeliveryInfoDto getOrderDeliveryInfo(String uuid){
        Delivery delivery = getBaseDelivery(uuid);
        return OrderDeliveryInfoDto.fromEntity(delivery);
    }

    @Override
    public DeliveryDetailDto getDetailDelivery(Long deliveryId) {
        Delivery delivery = getDelivery(deliveryId);
        return DeliveryDetailDto.fromEntity(delivery);
    }

    @Transactional
    @Override
    public void modifyDelivery(DeliveryModifyDto deliveryModifyDto) {
        Delivery delivery = getDelivery(deliveryModifyDto.getId());
        deliveryRepository.save(deliveryModifyDto.toEntity(delivery));
    }

    @Transactional
    @Override
    public void modifyBaseDelivery(BaseDeliveryModifyDto baseDeliveryModifyDto) {
        Delivery newBasedelivery = getDelivery(baseDeliveryModifyDto.getDeliveryId());
        // 기존 기본배송지 취소
        Delivery basedelivery = getBaseDelivery(newBasedelivery.getUsers().getUuid());
        deliveryRepository.save(baseDeliveryModifyDto.toEntity(basedelivery, (byte) 99));
        // 새 기본배송지 설정
        deliveryRepository.save(baseDeliveryModifyDto.toEntity(newBasedelivery, (byte) 11));
    }
    @Override
    public String getBaseDeliveryName(String uuid) {
        Delivery delivery = getBaseDelivery(uuid);
        return delivery.getDeliveryName();
    }

    private Delivery getBaseDelivery(String uuid) {
       return deliveryRepository.findByIsBaseDeliveryAndUsersUuid((byte) 11, uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_DELIVERY));
    }

    private Delivery getDelivery(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_DELIVERY));
    }
}
