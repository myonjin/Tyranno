package com.tyranno.ssg.users.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class Marketing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 1 : shinsegaeMarketingAgree
    // 2 : shinsegaeOptionAgree
    // 3 : ssgMarketingAgree

    @Column(nullable = false)
    private String type;

}
