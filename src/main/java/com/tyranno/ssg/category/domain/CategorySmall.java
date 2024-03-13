package com.tyranno.ssg.category.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "categorysmall")
public class CategorySmall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinTable(name = "category_middle")
    private CategoryMiddle categoryMiddle;

    @Column(name = "small_name")
    private String smallName;
}
