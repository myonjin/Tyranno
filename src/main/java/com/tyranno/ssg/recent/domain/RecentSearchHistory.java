package com.tyranno.ssg.recent.domain;

import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "recent_search_history")
public class RecentSearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinTable(name = "users")
    private Users users;

    @Column(name = "search_word", nullable = false)
    private String searchWord;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
