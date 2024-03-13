package com.tyranno.ssg.option.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "extra")
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "extra_name", nullable = false)
    private String extraName;

    @Column(name = "extra_price", nullable = false)
    private int extraPrice;

}
