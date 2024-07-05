package com.tyranno.ssg.delivery.application;

import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.delivery.dto.*;
import com.tyranno.ssg.delivery.dto.response.BaseDeliveryInfoDto;
import com.tyranno.ssg.delivery.dto.response.OrderDeliveryInfoDto;
import com.tyranno.ssg.users.domain.Users;

import java.util.List;

public interface DeliveryService {
    void addDelivery(DeliveryAddDto deliveryInfoDto, String uuid);
    void deleteDelivery(Long deliveryId);

    List<DeliveryListDto> getDeliveryList(String uuid);

    BaseDeliveryInfoDto getBaseDeliveryInfo(String uuid);

    OrderDeliveryInfoDto getOrderDeliveryInfo(String uuid);

    DeliveryDetailDto getDetailDelivery(Long deliveryId);

    void modifyDelivery(DeliveryModifyDto deliveryModifyDto);

    void modifyBaseDelivery(BaseDeliveryModifyDto baseDeliveryModifyDto);
  
    String getBaseDeliveryName(String uuid);
}
