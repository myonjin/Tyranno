package com.tyranno.ssg.users.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "marketing")
public class Marketing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 이 필드는 Users 엔티티의 id와 매핑됩니다.
    private Users users;

    @Column(name = "shinsegae_marketing_agree")
    private boolean shinsegaeMarketingAgree; // 신세계포인트 마케팅정보동의

    @Column(name = "shinsegae_marketing_agree_at")
    private LocalDateTime shinsegaeMarketingAgreeAt;

    @Column(name = "shinsegae_option_agree")
    private boolean shinsegaeOptionAgree;

    @Column(name = "shinsegae_option_agree_at")
    private LocalDateTime shinsegaeOptionAgreeAt;

    @Column(name = "ssg_marketing_agree")
    private boolean ssgMarketingAgree;

    @Column(name = "ssg_marketing_agree_at")
    private LocalDateTime ssgMarketingAgreeAt;

}
