package com.tyranno.ssg.category.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "category_middle")
public class CategoryMiddle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinTable(name = "category_large")
    private CategoryLarge categoryLarge;

    @Column(name = "middle_name")
    private String middleName;
}
