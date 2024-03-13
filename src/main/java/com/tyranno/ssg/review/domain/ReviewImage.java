package com.tyranno.ssg.review.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "review_image")
public class ReviewImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review; // 리뷰 테이블

    @Column(name = "review_image_url")
    private String reviewImageUrl; // 리뷰이미지 링크

    @Column(name = "priority")
    private int priority; // 우선순위
}
