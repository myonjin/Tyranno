package com.tyranno.ssg.product.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer discount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public Discount(Long id, Integer discount, Product product) {
        this.id = id;
        this.discount = discount;
        this.product = product;
    }
}
