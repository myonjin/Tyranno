package com.tyranno.ssg.category.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "category_middle")
public class CategoryMiddle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_large_id")
    private CategoryLarge categoryLarge;

    @Column(name = "middle_name")
    private String middleName;
}
