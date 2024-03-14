package com.tyranno.ssg.order.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usersId;

    private String addressRequest;

    @Column(nullable = false)
    private String addressBase;

    private String addressDetail;

    @Column(nullable = false)
    private Integer zipCode;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String receiverPhoneNumber;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String orderName;

    @Column(nullable = false)
    private String orderPhoneNumber;

    @Column(nullable = false)
    private String orderEmail;

    private String howToReceive;

    @Column(nullable = false)
    private String orderNumber;

    private Byte orderStatus;

    @Column(nullable = false)
    private Integer totalMoney;

    @Column(nullable = false)
    private Byte isOrderConfirm;
}
