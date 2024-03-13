package com.tyranno.ssg.option.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "`option`")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etc_id")
    private Etc etc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Extra_id")
    private Extra extra;

    @Column(name = "stock", nullable = false)
    private Integer stock;
}
