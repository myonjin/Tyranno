package com.tyranno.ssg.users.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String loginId;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Byte gender;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime modifyAt;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private String uuid;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Marketing marketing;

}
