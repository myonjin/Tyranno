package com.tyranno.ssg.users.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class Marketing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId") // 이 필드는 Users 엔티티의 id와 매핑됩니다.
    private Users users;

    private Byte shinsegaeMarketingAgree; // 신세계포인트 마케팅정보동의

    private LocalDateTime shinsegaeMarketingAgreeAt;

    private Byte shinsegaeOptionAgree;

    private LocalDateTime shinsegaeOptionAgreeAt;

    private Byte ssgMarketingAgree;

    private LocalDateTime ssgMarketingAgreeAt;
}
