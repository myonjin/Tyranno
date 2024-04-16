package com.tyranno.ssg.order.application;


import com.tyranno.ssg.cart.domain.Cart;
import com.tyranno.ssg.cart.infrastructure.CartRepository;
import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.option.infrastructure.OptionRepository;
import com.tyranno.ssg.order.domain.Order;
import com.tyranno.ssg.order.domain.OrderList;
import com.tyranno.ssg.order.dto.*;
import com.tyranno.ssg.order.infrastructure.OrderListRepository;
import com.tyranno.ssg.order.infrastructure.OrderRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tyranno.ssg.product.infrastructure.DiscountRepository;
import com.tyranno.ssg.product.infrastructure.ProductThumRepository;
import com.tyranno.ssg.vendor.infrastructure.VendorProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

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
    private final CartRepository cartRepository;

    @Override
    @Transactional
    public void addOrderList(OrderAddDto orderAddDto, String uuid) {
        // 2. 주문번호 생성 ( 짜침 )
        String createdOrderNumber = createOrderNumber(uuid);
        // 3. 생성된 주문번호를 넣어줘서 orderList 완성
        OrderList orderList = orderAddDto.toEntity(uuid, orderAddDto, createdOrderNumber);
        // 4. 저장된 orderList Id (PK) 값을 가져오기 위해서 저장
        OrderList savedOrderList = orderListRepository.save(orderList);

        // 5. CartList 가져오기
        List<Cart> CartList = cartRepository.findByUsersUuidAndIsKeep(uuid, (byte) 99);

        /** 주문리스트 번호와 옵션아이디 넣어준다
         *  개수와 가격도 넣어준다 stream으로
         */
        addOrder(savedOrderList, orderAddDto,CartList);



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

    private void addOrder(OrderList savedOrderList, OrderAddDto orderAddDto, List<Cart> CartList) {

        List<Long> orderOptionIds = new ArrayList<>();
        for (OptionIdListDto optionIdListDto : orderAddDto.getOptionIdList()) {
            Long optionId = optionIdListDto.getOptionId();
            // 주문한 옵션 아이디 리스트에 담아주기
            orderOptionIds.add(optionId);

            Option option = optionRepository.findById(optionId)
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_OPTION)); // 에러 처리
            Order order = optionIdListDto.toEntity(savedOrderList, option, optionIdListDto);
            orderRepository.save(order);
        }
        List<Long> willDeleteCartId = new ArrayList<>();

//        log.info("orderOptionIds : " + orderOptionIds);
//        log.info("CartList : " + CartList);
        for (Cart cart : CartList) {
            if (orderOptionIds.contains(cart.getOption().getId())) {
                willDeleteCartId.add(cart.getId());
            }
        }
//        log.info("willDeleteCartOptionId : " + willDeleteCartId);
        // 장바구니에서 삭제
        cartRepository.deleteByIdIn(willDeleteCartId);



    }

    public List<OrderListDto> getOrderList(String uuid) {
        List<OrderList> orderLists = orderListRepository.findAllByUuidOrderByCreatedAtDesc(uuid); // 주문 리스트
        List<OrderListDto> orderListDtos = new ArrayList<OrderListDto>(); // OrderListDto 객체들을 담을 리스트 생성

        for (OrderList orderList : orderLists) {
            log.info(orderList.toString());
            List<Order> orders = orderRepository.findAllByOrderListId(orderList.getId());
            List<OrderDto> orderDtoList = orders.stream()
                    .map(order -> {
                        Long productId = order.getOption().getProduct().getId();
                        String vendorName = vendorProductRepository.findByProductId(productId)
                                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_VENDOR)).getVendor()
                                .getVendorName();

                        String imgUrl = productThumRepository.findByProductIdAndPriority(productId, 1)
                                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCTTHUM))
                                .getImageUrl();

                        int discount = discountRepository.findByProductId(productId)
                                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_DISCOUNT))
                                .getDiscount();

                        return OrderDto.builder()
                                .orderId(order.getId())
                                .productId(productId)
                                .optionId(order.getOption().getId())
                                .count(order.getCount())
                                .money(order.getMoney())
                                .productName(order.getOption().getProduct().getProductName())
                                .price(order.getOption().getProduct().getProductPrice())
                                .vendorName(vendorName)
                                .imageUrl(imgUrl)
                                .discount(discount)
                                .build();
                    }).toList();

            // 생성된 OrderListDto를 리스트에 추가
            orderListDtos.add(OrderListDto
                    .builder()
                    .orderListId(orderList.getId())
                    .totalMoney(orderList.getTotalMoney())
                    .orderNumber(orderList.getOrderNumber())
                    .orderDate(orderList.getCreatedAt())
                    .receiverName(orderList.getReceiverName())
                    .orderDtoList(orderDtoList)
                    .isOrderConfirm(orderList.getIsOrderConfirm())
                    .orderStatus(orderList.getOrderStatus())
                    .build());
        }

        return orderListDtos; // 수정된 부분: OrderListDto 객체들을 담은 리스트 반환
    }


    public List<OrderListDto> getOrderList(ResponseNonOrderDto responseNonOrderDto) {

        List<OrderList> orderLists = orderListRepository.findByOrderNameAndOrderPhoneNumberAndOrderNumber
                (responseNonOrderDto.getOrderName(), responseNonOrderDto.getOrderPhoneNumber(), responseNonOrderDto.getOrderNumber()); // 주문 리스트

        List<OrderListDto> orderListDtos = new ArrayList<OrderListDto>(); // OrderListDto 객체들을 담을 리스트 생성
        for (OrderList orderList : orderLists) {
            log.info(orderList.toString());
            List<Order> orders = orderRepository.findAllByOrderListId(orderList.getId());
            List<OrderDto> orderDtoList = orders.stream()
                    .map(order -> {
                        Long productId = order.getOption().getProduct().getId();
                        String vendorName = vendorProductRepository.findByProductId(productId)
                                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_VENDOR)).getVendor()
                                .getVendorName();

                        String imgUrl = productThumRepository.findByProductIdAndPriority(productId, 1)
                                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCTTHUM))
                                .getImageUrl();

                        int discount = discountRepository.findByProductId(productId)
                                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_DISCOUNT))
                                .getDiscount();

                        return OrderDto.builder()
                                .orderId(order.getId())
                                .productId(productId)
                                .optionId(order.getOption().getId())
                                .count(order.getCount())
                                .money(order.getMoney())
                                .productName(order.getOption().getProduct().getProductName())
                                .price(order.getOption().getProduct().getProductPrice())
                                .vendorName(vendorName)
                                .imageUrl(imgUrl)
                                .discount(discount)
                                .build();
                    }).toList();

            // 생성된 OrderListDto를 리스트에 추가
            orderListDtos.add(OrderListDto
                    .builder()
                    .orderListId(orderList.getId())
                    .totalMoney(orderList.getTotalMoney())
                    .orderNumber(orderList.getOrderNumber())
                    .orderDate(orderList.getCreatedAt())
                    .receiverName(orderList.getReceiverName())
                    .orderDtoList(orderDtoList)
                    .isOrderConfirm(orderList.getIsOrderConfirm())
                    .orderStatus(orderList.getOrderStatus())
                    .build());
        }

        return orderListDtos; // 수정된 부분: OrderListDto 객체들을 담은 리스트 반환
    }


}
