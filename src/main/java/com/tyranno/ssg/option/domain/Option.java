package com.tyranno.ssg.option.domain;

import com.tyranno.ssg.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "`option`")
@Getter
@ToString
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Size size;

    @ManyToOne
    private Color color;

    @ManyToOne
    private Etc etc;

    @ManyToOne
    private Extra extra;

    @Column(nullable = false)
    private Integer stock;

}