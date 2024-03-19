package com.tyranno.ssg.users.domain;

import com.tyranno.ssg.global.GlobalTime;
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
public class MarketingInformation extends GlobalTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Byte isAgree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId") // 이 필드는 Users 엔티티의 id와 매핑됩니다.
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marketingId") // 이 필드는 Users 엔티티의 id와 매핑됩니다.
    private Marketing marketing;
}