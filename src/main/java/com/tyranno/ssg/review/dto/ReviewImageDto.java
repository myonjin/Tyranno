package com.tyranno.ssg.review.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ReviewImageDto {
    private int priority;
    private String imageUrl;

    @Builder

    public ReviewImageDto(int priority, String imageUrl) {
        this.priority = priority;
        this.imageUrl = imageUrl;
    }
}
