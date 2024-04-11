package com.tyranno.ssg.review.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.review.application.ReviewService;
import com.tyranno.ssg.review.dto.ReviewAbleOrderIdDto;
import com.tyranno.ssg.review.dto.ReviewCreateDto;
import com.tyranno.ssg.review.dto.ReviewInformationDto;
import com.tyranno.ssg.review.dto.ReviewPageDto;
import com.tyranno.ssg.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "리뷰", description = "review API")
@RequestMapping("/api/v1/review")
@Slf4j
public class ReviewController {
    private final JwtTokenProvider jwtTokenProvider;
    private final ReviewService reviewService;

    @Operation(summary = "리뷰 Id 조회", description = "상세페이지에서 리뷰 Id 조회,\n\n" +
            "sortCriterion 1: 평점 높은 순,\n\n" +
            "sortCriterion 2: 평점 낮은 순,\n\n" +
            "sortCriterion 3: 최근 순, \n\n" +
            "sortCriterion 4: 오래된 순")
    @GetMapping("/list/{product_id}")
    public ResponseEntity<?> getProductReviews(@PathVariable Long product_id,
                                               @RequestParam(defaultValue = "3") Integer sortCriterion,
                                               @RequestParam(defaultValue = "1") Integer page) {

        return new ResponseEntity<>(reviewService.getProductReviewIds(product_id, sortCriterion, page));
    }

    @Operation(summary = "리뷰 작성 페이지", description = "리뷰 작성 페이지 불러오기")
    @GetMapping("/add/{order_id}")
    public ResponseEntity<?> getReviewPage(@PathVariable Long order_id,  @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        ReviewPageDto reviewPageDto = reviewService.getReviewPage(order_id, uuid);

        return new ResponseEntity<>(reviewPageDto);
    }

    @Operation(summary = "리뷰 작성", description = "리뷰 작성 하기")
    @PostMapping("/add/{order_id}")
    public ResponseEntity<?> createReview(@PathVariable Long order_id, @RequestBody ReviewCreateDto reviewCreateDto,
                                          @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);

        String result = reviewService.addReview(order_id,reviewCreateDto, uuid);
        return  new ResponseEntity<>(result);
    }

    @Operation(summary = "리뷰 리스트용 정보", description = "리뷰 리스트용 정보 불러오기")
    @GetMapping("/reviewInformation/{review_id}")
    public ResponseEntity<?> getReviewInformation(@PathVariable Long review_id) {
        ReviewInformationDto reviewInformationDto = reviewService.getReviewInformation(review_id);
        return new ResponseEntity<>(reviewInformationDto);
    }

    @Operation(summary = "내가 작성한 리뷰 Id 조회", description = "마이페이지에서 리뷰 Id 조회,\n\n" +
            "sortCriterion 1: 평점 높은 순,\n\n" +
            "sortCriterion 2: 평점 낮은 순,\n\n" +
            "sortCriterion 3: 최근 순, \n\n" +
            "sortCriterion 4: 오래된 순")
    @GetMapping("/list/myReview")
    public ResponseEntity<?> getProductReviews(@RequestHeader("Authorization") String token,
                                               @RequestParam(defaultValue = "3") Integer sortCriterion,
                                               @RequestParam(defaultValue = "1") Integer page) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        return new ResponseEntity<>(reviewService.getUsersReviewIds(uuid, sortCriterion, page));
    }

    @Operation(summary = "작성 가능한 리뷰(주문 Id)", description = "작성 가능한 리뷰(주문 Id) 불러오기")
    @GetMapping("/list/able")
    public ResponseEntity<?> getAbleReviewPage(@RequestHeader("Authorization") String token,
                                               @RequestParam(defaultValue = "3") Integer sortCriterion,
                                               @RequestParam(defaultValue = "1") Integer page) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        ReviewAbleOrderIdDto reviewAbleOrderIdDto = reviewService.getReviewAbleOrderIds(uuid, sortCriterion, page);
        return new ResponseEntity<>(reviewAbleOrderIdDto);
    }

}
