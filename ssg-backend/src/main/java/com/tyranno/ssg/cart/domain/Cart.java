package com.tyranno.ssg.cart.domain;

import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users; // 회원 테이블(Users)

    @ManyToOne(fetch = FetchType.LAZY)
    private Option option; // 옵션 테이블

    private Integer count;

    private Byte isKeep;

    @Builder
    public Cart(Long id, Users users, Option option, Integer count, Byte isKeep) {
        this.id = id;
        this.users = users;
        this.option = option;
        this.count = count;
        this.isKeep = isKeep;
    }
}
