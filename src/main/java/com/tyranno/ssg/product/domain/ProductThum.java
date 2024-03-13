package com.tyranno.ssg.product.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_thum")
public class ProductThum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "image_url")
    private String imageUrl; // 이미지링크

    @Column(name = "priority", nullable = false)
    private Byte priority; // 우선순위

    @Column(name = "image_name")
    private String imageName; // alt
}
