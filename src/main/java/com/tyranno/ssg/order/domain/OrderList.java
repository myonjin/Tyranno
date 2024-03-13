package com.tyranno.ssg.order.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_list")
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "users_id")
    private long usersId;

    @Column(name = "address_request")
    private String addressRequest;

    @Column(name = "address_base", nullable = false)
    private String addressBase;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "zip_code", nullable = false)
    private Integer zipCode;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "receiver_phone_number", nullable = false)
    private String receiverPhoneNumber;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "order_name", nullable = false)
    private String orderName;

    @Column(name = "order_phone_number", nullable = false)
    private String orderPhoneNumber;

    @Column(name = "order_email", nullable = false)
    private String orderEmail;

    @Column(name = "how_to_receive")
    private String howToReceive;

    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @Column(name = "order_status")
    private byte orderStatus;

    @Column(name = "total_money", nullable = false)
    private Integer totalMoney;

    @Column(name = "is_order_confirm", nullable = false)
    private Boolean isOrderConfirm;
}
