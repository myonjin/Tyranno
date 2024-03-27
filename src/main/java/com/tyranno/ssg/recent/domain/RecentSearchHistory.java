package com.tyranno.ssg.recent.domain;

import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class RecentSearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersId")
    private Users users;

    @Column(nullable = false)
    private String searchWord;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
