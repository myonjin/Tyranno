package com.tyranno.ssg.review.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.review.application.ReviewService;
import com.tyranno.ssg.review.dto.ReviewCreateDto;
import com.tyranno.ssg.review.dto.ReviewInformationDto;
import com.tyranno.ssg.review.dto.ReviewPageDto;
import com.tyranno.ssg.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "리뷰", description = "review API")
@RequestMapping("/api/v1/review")
@Slf4j
public class ReviewController {
    private final JwtTokenProvider jwtTokenProvider;
    private final ReviewService reviewService;

    @Operation(summary = "리뷰 Id 조회", description = "상세페이지에서 리뷰 Id 조회")
    @GetMapping("/list/{product_id}")
    public ResponseEntity<?> getProductReviews(@PathVariable Long product_id,
                                               @RequestParam(defaultValue = "3") Integer sortCriterion,
                                               @RequestParam(required = false) Integer lastIndex) {

        return new ResponseEntity<>(reviewService.getProductReviewIds(product_id, sortCriterion, lastIndex));
    }

    @Operation(summary = "리뷰 작성 페이지", description = "리뷰 작성 페이지 불러오기")
    @GetMapping("/add/{product_id}")
    public ResponseEntity<?> getReviewPage(@PathVariable Long product_id,  @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        ReviewPageDto reviewPageDto = reviewService.getReviewPage(product_id, uuid);

        return new ResponseEntity<>(reviewPageDto);
    }

    @Operation(summary = "리뷰 작성", description = "리뷰 작성 하기")
    @PostMapping("/add/{product_id}")
    public ResponseEntity<?> createReview(@PathVariable Long product_id, @RequestBody ReviewCreateDto reviewCreateDto,
                                          @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);

        String result = reviewService.addReview(product_id,reviewCreateDto, uuid);
        return  new ResponseEntity<>(result);
    }

    @Operation(summary = "리뷰 리스트용 정보", description = "리뷰 리스트용 정보 불러오기")
    @GetMapping("/reviewInformation/{review_id}")
    public ResponseEntity<?> getReviewInformation(@PathVariable Long review_id) {
        ReviewInformationDto reviewInformationDto = reviewService.getReviewInformation(review_id);
        return new ResponseEntity<>(reviewInformationDto);
    }
}
