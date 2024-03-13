package com.tyranno.ssg.category.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "category_large")
public class CategoryLarge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "large_name")
    private String largeName;

    @Column(name = "large_url")
    private String largeUrl;
}
