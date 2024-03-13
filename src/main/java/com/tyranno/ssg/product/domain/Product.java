package com.tyranno.ssg.product.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name", nullable = false)
    private String productName; // 상품 이름

    @Column(name = "product_price", nullable = false)
    private int productPrice; //상품 가격

    @Column(name = "product_rate", nullable = false)
    private float productRate; // 평점

    @Column(name = "detail_content", nullable = false)
    private String detailContent; // 상세설명

    @Column(name = "review_count", nullable = false)
    private int reviewCount; // 리뷰 갯수
}
