package com.tyranno.ssg.category.domain;

import com.tyranno.ssg.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "detail_id")
    private long detailId;

    @Column(name = "small_id")
    private long smallId;

    @Column(name = "middle_id")
    private long middleId;

    @Column(name = "large_id")
    private long largeId;
}
