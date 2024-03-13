package com.tyranno.ssg.order.domain;

import com.tyranno.ssg.option.domain.Option;
import jakarta.persistence.*;
import lombok.*;

@Data
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
    @JoinColumn(name = "option_id")
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_list_id")
    private OrderList orderList;

    @Column(name = "content", nullable = false)
    private Integer count;

    @Column(name = "money", nullable = false)
    private Integer money;
}
