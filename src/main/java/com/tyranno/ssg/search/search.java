package com.tyranno.ssg.search;

import com.tyranno.ssg.global.GlobalCreateTime;
import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class search extends GlobalCreateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @Column(nullable = false)
    private String searchWord;
}
