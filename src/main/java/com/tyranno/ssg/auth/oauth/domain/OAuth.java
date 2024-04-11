package com.tyranno.ssg.auth.oauth.domain;

import com.tyranno.ssg.global.GlobalCreateTime;
import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class OAuth extends GlobalCreateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Byte type;

    @Column(nullable = false)
    private Long externalId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @Builder
    public OAuth(Long id, Byte type, Long externalId, Users users) {
        this.id = id;
        this.type = type;
        this.externalId = externalId;
        this.users = users;
    }
}
