package com.tyranno.ssg.order.domain;

import com.tyranno.ssg.global.GlobalCreateTime;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@ToString
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

//    @CreatedDate
//    @Column(nullable = false)
//    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String orderName;

    @Column(nullable = false)
    private String orderPhoneNumber;

    @Column(nullable = false)
    private String orderEmail;

    @Column(nullable = false)
    private String orderNumber; // 주문 번호

    private Byte orderStatus; // 주문상태 0결제완료 / 1상품준비중 / 2배송준비중 / 3배송중 / 4배송완료

    @Column(nullable = false)
    private Integer totalMoney;

    @Column(nullable = false)
    private Byte isOrderConfirm;


//    public OrderList(String createdOrderNumber) {
//        this.orderNumber = createdOrderNumber;
//    }

}
