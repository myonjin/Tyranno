package com.tyranno.ssg.review.domain;

import com.tyranno.ssg.global.GlobalCreateTime;
import com.tyranno.ssg.order.domain.Order;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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
    private Float rate; // 별점

    @Column(nullable = false)
    private String content; // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Builder
    public Review(Long id, Users users, Product product, Float rate, String content, Order order) {
        this.id = id;
        this.users = users;
        this.product = product;
        this.rate = rate;
        this.content = content;
        this.order = order;
    }
}
