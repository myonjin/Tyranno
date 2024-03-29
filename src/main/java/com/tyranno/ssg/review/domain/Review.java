package com.tyranno.ssg.review.domain;

import com.tyranno.ssg.global.GlobalCreateTime;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class Review extends GlobalCreateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users; // 회원 테이블

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product; // 상품 테이블

    @Column(nullable = false)
    private Byte rate; // 별점

    @Column(nullable = false)
    private String content; // 내용
}
