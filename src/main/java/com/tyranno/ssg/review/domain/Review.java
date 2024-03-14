package com.tyranno.ssg.review.domain;

import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersId")
    private Users users; // 회원 테이블

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product; // 상품 테이블

    @Column(nullable = false)
    private Byte rate; // 별점

    @Column(nullable = false)
    private String content; // 내용

    @Column(nullable = false)
    private LocalDateTime rgdt; // 작성일자
}
