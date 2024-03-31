package com.tyranno.ssg.order.domain;

import com.tyranno.ssg.global.GlobalCreateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class OrderList extends GlobalCreateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uuid;

    private String deliveryRequest;

    @Column(nullable = false)
    private String deliveryBase;

    private String deliveryDetail;

    @Column(nullable = false)
    private Integer zipCode;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String receiverPhoneNumber;

    @CreatedDate
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


//    public OrderList(String createdOrderNumber) {
//        this.orderNumber = createdOrderNumber;
//    }

}
