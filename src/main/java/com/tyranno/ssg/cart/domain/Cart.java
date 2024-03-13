package com.tyranno.ssg.cart.domain;

import com.tyranno.ssg.auth.users.domain.Users;
import com.tyranno.ssg.option.domain.Option;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users; // 회원 테이블(Users)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private Option option; // 옵션 테이블

    @Column(name = "count")
    private int count;

    @Column(name = "is_included", nullable = false)
    private Boolean isIncluded;

    @Column(name = "is_keep")
    private boolean isKeep;
}
