package com.tyranno.ssg.review.application;

import com.tyranno.ssg.review.dto.ReviewCreateDto;
import com.tyranno.ssg.review.dto.ReviewIdListDto;
import com.tyranno.ssg.review.dto.ReviewInformationDto;
import com.tyranno.ssg.review.dto.ReviewPageDto;

public interface ReviewService {
    ReviewIdListDto getProductReviewIds(Long product_id, Integer sortCriterion, Integer lastIndex);

    ReviewPageDto getReviewPage(Long productId, String uuid);

    String addReview(Long productId, ReviewCreateDto reviewCreateDto, String uuid);

    ReviewInformationDto getReviewInformation(Long reviewId);
}
