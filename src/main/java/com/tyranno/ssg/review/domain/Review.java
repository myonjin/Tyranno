package com.tyranno.ssg.review.domain;

import com.tyranno.ssg.auth.users.domain.Users;
import com.tyranno.ssg.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users; // 회원 테이블

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 상품 테이블

    @Column(name = "rate", nullable = false)
    private Byte rate; // 별점

    @Column(name = "content", nullable = false)
    private String content; // 내용

    @Column(name = "rgdt", nullable = false)
    private LocalDateTime rgdt; // 작성일자
}
