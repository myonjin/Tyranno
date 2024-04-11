package com.tyranno.ssg.review.application;

import com.tyranno.ssg.review.dto.*;

import java.util.List;

public interface ReviewService {
    ReviewIdListDto getProductReviewIds(Long productId, Integer sortCriterion, Integer page);
    ReviewPageDto getReviewPage(Long orderId, String uuid);
    String addReview(Long orderId, ReviewCreateDto reviewCreateDto, String uuid);
    ReviewInformationDto getReviewInformation(Long reviewId);
    ReviewIdListDto getUsersReviewIds(String uuid, Integer sortCriterion, Integer page);
//    ReviewAbleOrderDto getReviewAbleOrderIds(String uuid);
    ReviewAbleOrderIdDto getReviewAbleOrderIds(String uuid, Integer sortCriterion, Integer page);
}
