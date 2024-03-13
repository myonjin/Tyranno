package com.tyranno.ssg.product.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount")
    private int discount;

    @Column(name = "discount_start")
    private LocalDateTime discountStart;

    @Column(name = "discount_end")
    private LocalDateTime discountEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
