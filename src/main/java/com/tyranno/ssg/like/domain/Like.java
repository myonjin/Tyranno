package com.tyranno.ssg.like.domain;

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
@Table(name = "`like`")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public Like(Long id, Users users, Product product) {
        this.id = id;
        this.users = users;
        this.product = product;
    }
}
