package com.tyranno.ssg.delivery.application;

import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.delivery.dto.*;
import com.tyranno.ssg.users.domain.Users;

import java.util.List;

public interface DeliveryService {
    void addDelivery(DeliveryAddDto deliveryInfoDto, String uuid);
    void deleteDelivery(Long deliveryId);
    List<DeliveryListDto> getDeliveryList(String uuid);
    DeliveryDetailDto getDetailDelivery(Long deliveryId);
    void modifyDelivery(DeliveryModifyDto deliveryModifyDto);
    void modifyBaseDelivery(BaseDeliveryModifyDto baseDeliveryModifyDto);
    Delivery getDelivery(Long deliveryId);
    Users getUsers(String uuid);

}
