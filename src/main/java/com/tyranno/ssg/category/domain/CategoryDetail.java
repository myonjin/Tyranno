package com.tyranno.ssg.category.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categorydetail")
public class CategoryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_small_id")
    private CategorySmall categorySmall;

    @Column(name = "detail_name")
    private String detailName;
}
