package com.tyranno.ssg.address.domain;

import com.tyranno.ssg.auth.users.domain.Users;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users; // 회원정보 들고오기

    @Column(name = "is_main")
    private boolean isMain;

    @Column(name = "address_name")
    private String addressName;

    @Column(name = "zip_code", nullable = false)
    private int zipCode;

    @Column(name = "address_base", nullable = false)
    private String addressBase;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "home_number")
    private String homeNumber;
}
