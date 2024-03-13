package com.tyranno.ssg.option.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "etc")
public class Etc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "additional_option", nullable = false)
    private String additionalOption;
}
