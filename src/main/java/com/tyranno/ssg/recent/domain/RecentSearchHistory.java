package com.tyranno.ssg.recent.domain;

import com.tyranno.ssg.global.GlobalCreateTime;
import com.tyranno.ssg.users.domain.Users;
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
public class RecentSearchHistory extends GlobalCreateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @Column(nullable = false)
    private String searchWord;
}
