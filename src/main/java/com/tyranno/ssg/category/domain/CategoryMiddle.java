package com.tyranno.ssg.category.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class CategoryMiddle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryLarge categoryLarge;

    private String middleName;
    private String middleImageUrl;


}