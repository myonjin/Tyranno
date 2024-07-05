package com.tyranno.ssg.recent.domain;

import com.tyranno.ssg.global.GlobalCreateTime;
import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class RecentViewedProduct extends GlobalCreateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false)
    private Byte isView;

    @Builder
    public RecentViewedProduct(Long id, Users users, Product product, Byte isView) {
        this.id = id;
        this.users = users;
        this.product = product;
        this.isView = isView;
    }

}
