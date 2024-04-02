package com.tyranno.ssg.order.application;


import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.option.infrastructure.OptionRepository;
import com.tyranno.ssg.order.domain.Order;
import com.tyranno.ssg.order.domain.OrderList;
import com.tyranno.ssg.order.dto.OptionIdListDto;
import com.tyranno.ssg.order.dto.OrderAddDto;
import com.tyranno.ssg.order.dto.OrderDto;
import com.tyranno.ssg.order.dto.OrderListDto;
import com.tyranno.ssg.order.infrastructure.OrderListRepository;
import com.tyranno.ssg.order.infrastructure.OrderRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;

import com.tyranno.ssg.product.domain.ProductThum;
import com.tyranno.ssg.product.infrastructure.DiscountRepository;
import com.tyranno.ssg.product.infrastructure.ProductThumRepository;
import com.tyranno.ssg.vendor.domain.VendorProduct;
import com.tyranno.ssg.vendor.infrastructure.VendorProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderListRepository orderListRepository;
    private final OrderRepository orderRepository;
    private final OptionRepository optionRepository;
    private final VendorProductRepository vendorProductRepository;
    private final ProductThumRepository productThumRepository;
    private final DiscountRepository discountRepository;

    @Override
    @Transactional
    public void addOrderList(OrderAddDto orderAddDto, String uuid) {
        // 2. 주문번호 생성 ( 짜침 )
        String createdOrderNumber = createOrderNumber(uuid);
        // 3. 생성된 주문번호를 넣어줘서 orderList 완성
        OrderList orderList = orderAddDto.toEntity(uuid, orderAddDto, createdOrderNumber);
        // 4. 저장된 orderList Id (PK) 값을 가져오기 위해서 저장
        OrderList savedOrderList = orderListRepository.save(orderList);

        /** 주문리스트 번호와 옵션아이디 넣어준다
         *  개수와 가격도 넣어준다 stream으로
         */
        addOrder(savedOrderList, orderAddDto);

    }

    private String createOrderNumber(String uuid) {
        //날짜 + uuid + 랜덤 숫자
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
        // 현재 시간(한국 시간대) 가져오기
        ZonedDateTime nowInKorea = ZonedDateTime.now(koreaZoneId);
        // 날짜 포맷 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = nowInKorea.format(formatter);
        // uuid 마지막 6자리 가져오기
        String orderUuidNumber = uuid.substring(uuid.length() - 6);
        // 랜덤 숫자 생성
        int randomNumber = new Random().nextInt(9000) + 1000;
        return formattedDate + "-" + orderUuidNumber + "-" + randomNumber;
    }

    private void addOrder(OrderList savedOrderList, OrderAddDto orderAddDto) {
        for (OptionIdListDto optionIdListDto : orderAddDto.getOptionIdList()) {
            Option option = optionRepository.findById(optionIdListDto.getOptionId())
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_OPTION)); // 에러 처리
            Order order = optionIdListDto.toEntity(savedOrderList, option, optionIdListDto);
            orderRepository.save(order);
        }
    }

    public OrderListDto getOrderList(String uuid) {
        OrderList orderList = orderListRepository.findByUuid(uuid)  //주문 리스트
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_ORDER_LIST)); // 에러 처리

        //주문 목록 불러오기
        List<Order> orders = orderRepository.findAllByOrderListId(orderList.getId());
        //vendor 불러오기


        List<OrderDto> orderDtoList = orders.stream()
                .map(order -> {

                    Long productId = order.getOption().getProduct().getId();
                    String vendorName = vendorProductRepository.findByProductId(productId)
                            .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_VENDOR)).getVendor().getVendorName();

                    String imgUrl = productThumRepository.findByProductIdAndPriority(productId, 1)
                            .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCTTHUM)).getImageUrl();

//                    int discount = discountRepository.

                    return OrderDto.builder()
                            .orderId(order.getId())
                            .productId(order.getOption().getProduct().getId())
                            .optionId(order.getOption().getId())
                            .count(order.getCount())
                            .money(order.getMoney())
                            .productName(order.getOption().getProduct().getProductName())
                            .price(order.getOption().getProduct().getProductPrice())
//                                    .vendorName(order.getOption().getProduct().get)
//                                    .imageUrl(order.getOption().getProduct().get())
//                                    .discount(order.getOption().getProduct().get())
                            .build();
                }).collect(Collectors.toList());

//                        VendorProduct vendorProduct = vendorProductRepository.findByProductId();
//                        VendorDto vendorDto = new VendorDto();
//
//                         OrderDto.builder()
//                        .orderId(order.getId())
//                        .productId(order.getOption().getProduct().getId())
//                        .optionId(order.getOption().getId())
//                        .count(order.getCount())
//                        .money(order.getMoney())
//                        .productName(order.getOption().getProduct().getProductName())
//                        .price(order.getOption().getProduct().getProductPrice())
//                        .vendorName(order.getOption().getProduct().get)
//                        .imageUrl(order.getOption().getProduct().get())
//                        .discount(order.getOption().getProduct().get())
//                        .build())
//                .toList();
//                );
        return OrderListDto
                .builder()
                .orderListId(orderList.getId())
                .totalMoney(orderList.getTotalMoney())
                .orderNumber(orderList.getOrderNumber())
                .orderDate(orderList.getCreatedAt())
                .receiverName(orderList.getReceiverName())
                .orderDtoList(orderDtoList)
                .isOrderConfirm(orderList.getIsOrderConfirm())
                .orderStatus(orderList.getOrderStatus())
                .build();
    }


}
