package com.tyranno.ssg.category.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categorysmall")
public class CategorySmall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_middle_id")
    private CategoryMiddle categoryMiddle;

    @Column(name = "small_name")
    private String smallName;
}
