package com.tyranno.ssg.review.dto;

import com.tyranno.ssg.review.domain.ReviewImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReviewCreateDto {
    private String content;
    private Float rate;
    private List<String> reviewImages;

//    @Builder
//    public ReviewCreateDto(Long productId, Long usersId, String content, Float rate, Long orderId, int priority, String imageUrl) {
//        this.productId = productId;
//        this.usersId = usersId;
//        this.content = content;
//        this.rate = rate;
//        this.orderId = orderId;
//        this.priority = priority;
//        this.imageUrl = imageUrl;
//    }
}
