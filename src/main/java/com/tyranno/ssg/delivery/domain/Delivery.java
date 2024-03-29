package com.tyranno.ssg.delivery.domain;

import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Entity
@Getter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users; // 회원정보 들고오기

    @Column(nullable = false)
    private Byte isBaseDelivery;

    @Column(nullable = false)
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

    @Builder
    public Delivery(Long id, Users users, Byte isBaseDelivery, String deliveryName, Integer zipCode, String deliveryBase, String deliveryDetail, String receiverName, String phoneNumber, String homeNumber) {
        this.id = id;
        this.users = users;
        this.isBaseDelivery = isBaseDelivery;
        this.deliveryName = deliveryName;
        this.zipCode = zipCode;
        this.deliveryBase = deliveryBase;
        this.deliveryDetail = deliveryDetail;
        this.receiverName = receiverName;
        this.phoneNumber = phoneNumber;
        this.homeNumber = homeNumber;
    }
}
