package com.tyranno.ssg.review.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.option.infrastructure.OptionRepository;
import com.tyranno.ssg.order.domain.Order;
import com.tyranno.ssg.order.domain.OrderList;
import com.tyranno.ssg.order.infrastructure.OrderListRepository;
import com.tyranno.ssg.order.infrastructure.OrderRepository;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.product.domain.ProductThum;
import com.tyranno.ssg.product.dto.ProductIdListDto;
import com.tyranno.ssg.product.infrastructure.ProductRepository;
import com.tyranno.ssg.product.infrastructure.ProductThumRepository;
import com.tyranno.ssg.review.domain.Review;
import com.tyranno.ssg.review.domain.ReviewImage;
import com.tyranno.ssg.review.dto.ReviewCreateDto;
import com.tyranno.ssg.review.dto.ReviewIdListDto;
import com.tyranno.ssg.review.dto.ReviewImageDto;
import com.tyranno.ssg.review.dto.ReviewPageDto;
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
import java.util.stream.Collectors;

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
    private final ReviewImageRepository reviewimageRepository;
    private final ReviewRepositoryImp reviewRepositoryImp;

    @Override
    public ReviewIdListDto getProductReviewIds(Long productId, Integer sortCriterion, Integer lastIndex) {
        List<Long> reviewIds = reviewRepositoryImp.searchReviewIdsByProductId(productId, sortCriterion, lastIndex);

        // 로그 추가: reviewIds 확인
        log.info("Retrieved reviewIds: {}", reviewIds);

        List<Map<String, Long>> reviewIdList = new ArrayList<>();
        for (int i = 0; i < reviewIds.size(); i++) {
            Map<String, Long> reviewMap = new HashMap<>();
            reviewMap.put("reviewId" + (i + 1), reviewIds.get(i));
            reviewIdList.add(reviewMap);
        }

        return ReviewIdListDto.builder()
                .reviewIds(reviewIdList)
                .build();
    }




    @Override
    public ReviewPageDto getReviewPage(Long productId, String uuid) {
        // 사용자 정보 가져오기
        Users users = usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));

        // 주문 목록 가져오기
        List<OrderList> orderLists = orderListRepository.findAllByUuid(uuid);

        // 주문 목록이 없을 경우 예외 처리
        if (orderLists.isEmpty()) {
            throw new GlobalException(ResponseStatus.NO_EXIST_ORDERS);
        }

        // 주문 목록에서 각 주문의 ID를 추출하여 리스트로 저장
        List<Long> orderListIds = orderLists.stream()
                .map(OrderList::getId)
                .toList();

        // 제품 정보 출력 여부를 나타내는 플래그
        boolean productInfoPrinted = false;

        // ReviewPageDto 생성
        ReviewPageDto reviewPageDto = new ReviewPageDto();

        // 주문 목록을 순회하며 해당 제품에 대한 리뷰를 작성할 옵션을 찾아 로그로 출력
        for (Long orderListId : orderListIds) {
            List<Order> orders = orderRepository.findByOrderListIdAndIsReview(orderListId, 99);
            for (Order order : orders) {
                // 주문이 해당 제품인지 확인
                if (productId.equals(order.getOption().getProduct().getId())) {
                    if (!productInfoPrinted) {
                        // 제품 정보 설정
                        String productName = order.getOption().getProduct().getProductName();
                        reviewPageDto.setProductName(productName);
                        log.info("Product Name: {}", productName);

                        // ProductThumb에서 썸네일 정보 조회
                        Optional<ProductThum> productThum = productThumRepository
                                .findByProductIdAndPriority(order.getOption().getProduct().getId(), 1);
                        String productThumUrl = productThum.orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCTTHUM)).getImageUrl();
                        reviewPageDto.setProductThum(productThumUrl);
                        log.info("Product Thum URL: {}", productThumUrl);

                        // orderNumber와 orderDate 설정
                        reviewPageDto.setOrderNumber(order.getOrderList().getOrderNumber());
                        reviewPageDto.setOrderDate(order.getOrderList().getCreatedAt());
                        productInfoPrinted = true;
                    }

                    // Option ID 추가
                    String optionKey = "optionId" + (reviewPageDto.getOptionIds().size() + 1);
                    reviewPageDto.getOptionIds().put(optionKey, order.getOption().getId());
                    log.info("Option ID: {}", order.getOption().getId());
                }
            }
        }
        if (!productInfoPrinted) {
            throw new GlobalException(ResponseStatus.NO_EXIST_ORDERPRODUCT);
        }
        // 로그로 ReviewPageDto 출력
        log.info("ReviewPageDto: {}", reviewPageDto);

        return reviewPageDto;
    }

    @Override
    @Transactional
    public String addReview(Long productId, ReviewCreateDto reviewCreateDto, String uuid) {
        try {
            // 상품 조회
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCT));

            // 회원 조회
            Users user = usersRepository.findByUuid(uuid)
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));

            // 주문 조회
            Order order = orderRepository.findById(reviewCreateDto.getOrderId())
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_ORDERS));

            // 리뷰 생성
            Review review = Review.builder()
                    .product(product)
                    .users(user)
                    .rate(reviewCreateDto.getRate())
                    .content(reviewCreateDto.getContent())
                    .order(order)
                    .build();

            // 첫 번째 이미지의 우선 순위를 1로 설정
            boolean isFirstImage = true;
            for (ReviewImageDto imageDto : reviewCreateDto.getReviewImages()) {
                ReviewImage reviewImage = ReviewImage.builder()
                        .priority(isFirstImage ? 1 : 0)
                        .reviewImageUrl(imageDto.getImageUrl())
                        .review(review)
                        .build();

                reviewimageRepository.save(reviewImage);
                isFirstImage = false;
            }

            // 리뷰 저장
            reviewRepository.save(review);

            return "리뷰 저장 성공!";
        } catch (Exception e) {
            throw new GlobalException(ResponseStatus.NO_EXIST_ORDERPRODUCT);
        }
    }



}
