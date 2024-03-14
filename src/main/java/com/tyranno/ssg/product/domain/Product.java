package com.tyranno.ssg.product.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName; // 상품 이름

    @Column(nullable = false)
    private Integer productPrice; //상품 가격

    @Column(nullable = false)
    private Float productRate; // 평점

    @Column(nullable = false)
    private String detailContent; // 상세설명

    @Column(nullable = false)
    private Integer reviewCount; // 리뷰 갯수
}
