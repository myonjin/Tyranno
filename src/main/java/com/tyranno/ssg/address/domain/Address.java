package com.tyranno.ssg.address.domain;

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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users; // 회원정보 들고오기

    private Byte isMain;

    private String addressName;

    @Column(nullable = false)
    private Integer zipCode;

    @Column(nullable = false)
    private String addressBase;

    private String addressDetail;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String phoneNumber;

    private String homeNumber;
}
