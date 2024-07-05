package com.tyranno.ssg.review.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.option.dto.OptionNamesDto;
import com.tyranno.ssg.option.infrastructure.OptionRepository;
import com.tyranno.ssg.order.domain.Order;
import com.tyranno.ssg.order.domain.OrderList;
import com.tyranno.ssg.order.infrastructure.OrderListRepository;
import com.tyranno.ssg.order.infrastructure.OrderRepository;
import com.tyranno.ssg.product.application.ProductService;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.domain.ProductThum;
import com.tyranno.ssg.product.infrastructure.ProductRepository;
import com.tyranno.ssg.product.infrastructure.ProductThumRepository;
import com.tyranno.ssg.review.domain.Review;
import com.tyranno.ssg.review.domain.ReviewImage;
import com.tyranno.ssg.review.dto.*;
import com.tyranno.ssg.review.infrastructure.ReviewImageRepository;
import com.tyranno.ssg.review.infrastructure.ReviewRepository;
import com.tyranno.ssg.review.infrastructure.ReviewRepositoryImp;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImp implements ReviewService{
    private final UsersRepository usersRepository;
    private final ProductRepository productRepository;
    private final ProductThumRepository productThumRepository;
    private final OptionRepository optionRepository;
    private final OrderRepository orderRepository;
    private final OrderListRepository orderListRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ReviewRepositoryImp reviewRepositoryImp;
    private final ProductService productService;

    public ReviewIdListDto getProductReviewIds(Long productId, Integer sortCriterion, Integer page) {
        final int PAGE_SIZE = 10; // 페이지당 항목 수 상수 선언

        List<Long> reviewIds = reviewRepositoryImp.searchReviewIdsByProductId(productId, sortCriterion, page);
        List<Map<String, Object>> reviewIdList = new ArrayList<>();
        int startIndex = (page - 1) * PAGE_SIZE; // 페이지 번호가 1부터 시작하므로 수정
        for (int i = 0; i < reviewIds.size(); i++) {
            Long reviewId = reviewIds.get(i);
            Map<String, Object> reviewMap = new HashMap<>();
            reviewMap.put("reviewId", reviewId);
            reviewMap.put("idx", startIndex + i + 1); // 인덱스도 1부터 시작하도록 수정
            reviewIdList.add(reviewMap);
        }

        return ReviewIdListDto.builder()
                .reviewIds(reviewIdList)
                .build();
    }
    @Override
    public ReviewPageDto getReviewPage(Long orderId,String uuid) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_ORDERS));
        if(!Objects.equals(order.getOrderList().getUuid(), uuid)){
            throw new GlobalException(ResponseStatus.USER_HAS_NOT_PURCHASED_THE_PRODUCT);
        }
        Product product = order.getOption().getProduct();

        Optional<ProductThum> productThumOptional = productThumRepository.findByProductIdAndPriority(product.getId(), 1);
        String productThum = null;
        if (productThumOptional.isPresent()) {
            productThum = productThumOptional.get().getImageUrl();
        }
        OptionNamesDto optionNamesDto = optionRepository
                .findById(order.getOption().getId())
                .map(OptionNamesDto::FromEntity)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_OPTION));


        return ReviewPageDto.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .productThum(productThum)
                .orderDate(order.getOrderList().getCreatedAt())
                .orderNumber(order.getOrderList().getOrderNumber())
                .optionNamesDto(optionNamesDto)
                .build();
    }
    @Override
    @Transactional
    public String addReview(Long orderId, ReviewCreateDto reviewCreateDto, String uuid) {
        try {
            // 상품 조회
            Product product = orderRepository.findById(orderId)
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_ORDERS))
                    .getOption().getProduct();
            log.info(product.getDetailContent());

            // 회원 조회
            Users users = getUsers(uuid);

            // 주문 조회 및 확인
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_ORDERS));
            if (!Objects.equals(order.getOrderList().getUuid(), uuid)) {
                throw new GlobalException(ResponseStatus.USER_HAS_NOT_PURCHASED_THE_PRODUCT);
            }

            // 리뷰 생성
            Review review = Review.builder()
                    .product(product)
                    .users(users)
                    .rate(reviewCreateDto.getRate())
                    .content(reviewCreateDto.getContent())
                    .order(order)
                    .build();

            // 리뷰 이미지 저장
            int priority = 1; // 첫 번째 이미지의 우선 순위는 1로 설정
            for (String imageUrl : reviewCreateDto.getReviewImages()) {
                ReviewImage reviewImage = ReviewImage.builder()
                        .priority(priority)
                        .reviewImageUrl(imageUrl)
                        .review(review)
                        .build();

                reviewImageRepository.save(reviewImage);
                priority = 0; // 첫 번째 이미지 이외의 이미지는 우선 순위를 0으로 설정
            }

            // 리뷰 저장
            reviewRepository.save(review);

            // 평점 및 리뷰 수 업데이트
            productService.updateProductRatingAndReviewCount(product.getId(), reviewCreateDto.getRate());
            order = Order.builder()
                    .id(orderId)
                    .count(order.getCount())
                    .money(order.getMoney())
                    .option(order.getOption())
                    .orderList(order.getOrderList())
                    .isReview((byte) 99)
                    .build();
            orderRepository.save(order);
            return "리뷰 저장 성공!";
        } catch (Exception e) {
            throw new GlobalException(ResponseStatus.FAILED_TO_REVIEW);
        }
    }

    @Override
    public ReviewInformationDto getReviewInformation(Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        Review review = reviewOptional.orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCT));

        List<ReviewImage> reviewImages = reviewImageRepository.findAllByReviewId(reviewId);
        // ReviewImage를 ReviewImageDto로 변환
        List<ReviewImageDto> reviewImageDtos = new ArrayList<>();
        for (ReviewImage reviewImage : reviewImages) {
            ReviewImageDto reviewImageDto = ReviewImageDto.builder()
                    .priority(reviewImage.getPriority())
                    .imageUrl(reviewImage.getReviewImageUrl())
                    .build();
            reviewImageDtos.add(reviewImageDto);
        }

        // OptionNamesDto 들고오기
        OptionNamesDto optionNamesDto = optionRepository
                .findById(review.getOrder().getOption().getId())
                .map(OptionNamesDto::FromEntity)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_OPTION));

        return ReviewInformationDto.builder()
                .rate(review.getRate())
                .createdAt(review.getCreatedAt())
                .id(review.getId())
                .orderNumber(review.getOrder().getOrderList().getOrderNumber())
                .optionNamesDto(optionNamesDto)
                .loginId(review.getUsers().getLoginId())
                .content(review.getContent())
                .reviewImageDtos(reviewImageDtos)
                .build();
    }
    public ReviewIdListDto getUsersReviewIds(String uuid, Integer sortCriterion, Integer page) {
        final int PAGE_SIZE = 10; // 페이지당 항목 수 상수 선언
        Users users = getUsers(uuid);
        List<Long> reviewIds = reviewRepositoryImp.searchReviewIdsByUsersId(users.getId(), sortCriterion, page);
        List<Map<String, Object>> reviewIdList = new ArrayList<>();
        int startIndex = (page - 1) * PAGE_SIZE; // 페이지 번호가 1부터 시작
        for (int i = 0; i < reviewIds.size(); i++) {
            Long reviewId = reviewIds.get(i);
            Map<String, Object> reviewMap = new HashMap<>();
            reviewMap.put("reviewId", reviewId);
            reviewMap.put("idx", startIndex + i + 1); // 인덱스도 1부터 시작
            reviewIdList.add(reviewMap);
        }

        return ReviewIdListDto.builder()
                .reviewIds(reviewIdList)
                .build();
    }
    public ReviewAbleOrderIdDto getReviewAbleOrderIds(String uuid, Integer sortCriterion, Integer page) {
        final int PAGE_SIZE = 10;
        List<OrderList> orderLists = orderListRepository.findAllByUuidOrderByCreatedAtDesc(uuid);
        List<Map<String, Object>> orderIdList = new ArrayList<>();
        int startIndex = (page - 1) * PAGE_SIZE + 1;

        for (OrderList orderList : orderLists) {
            List<Order> orders = orderRepository.findByOrderListIdAndIsReview(orderList.getId(), 99);
            for (Order order : orders) {
                Map<String, Object> orderMap = new HashMap<>();
                orderMap.put("orderId", order.getId());
                orderMap.put("idx", startIndex++);
                orderIdList.add(orderMap);
            }
        }

        return ReviewAbleOrderIdDto.builder()
                .reviewAbleOrderIds(orderIdList)
                .build();
    }

    private Users getUsers(String uuid) {
        return usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
    }
}
