package com.tyranno.ssg.payment.domain;

import com.tyranno.ssg.order.domain.OrderList;
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
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String partnerOrderId;

    @Column(nullable = false)
    private String partnerUserId;

    @Column(nullable = false)
    private String tid;

    @OneToOne(fetch = FetchType.LAZY)
    private OrderList orderList;
}
