package com.tyranno.ssg.review.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class ReviewImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review; // 리뷰 테이블

    private String reviewImageUrl; // 리뷰이미지 링크

    private Integer priority; // 우선순위
}
