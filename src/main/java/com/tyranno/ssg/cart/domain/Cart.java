package com.tyranno.ssg.cart.domain;

import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users; // 회원 테이블(Users)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "optionId")
    private Option option; // 옵션 테이블

    private Integer count;

    @Column(nullable = false)
    private Byte isIncluded;

    private Byte isKeep;
}
