package com.tyranno.ssg.category.domain;

import com.tyranno.ssg.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinTable(name = "product")
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
