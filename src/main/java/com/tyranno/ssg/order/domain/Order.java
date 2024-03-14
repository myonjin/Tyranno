package com.tyranno.ssg.order.domain;

import com.tyranno.ssg.option.domain.Option;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "optionId")
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderListId")
    private OrderList orderList;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private Integer money;
}
