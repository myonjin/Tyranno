package com.tyranno.ssg.delivery.domain;

import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users; // 회원정보 들고오기

    private Byte isBaseDelivery;

    private String deliveryName;

    @Column(nullable = false)
    private Integer zipCode;

    @Column(nullable = false)
    private String deliveryBase;

    private String deliveryDetail;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String phoneNumber;

    private String homeNumber;
}
