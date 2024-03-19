package com.tyranno.ssg.question.domain;

import com.tyranno.ssg.product.domain.Product;
import com.tyranno.ssg.users.domain.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Q&A 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersId")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    private String title; // 제목

    @Column(nullable = false)
    private String content; // 내용

    @Column(nullable = false)
    private LocalDateTime rgdt; // 작성일

    private LocalDateTime modifyRgdt; // 수정일

    @Column(nullable = false)
    private Byte isDelete; // 삭제 여부

    @Column(nullable = false)
    private Byte isPrivate; // 비밀 여부

    private Long parentId; // 답변에 대한 질문 아이디

    @Column(nullable = false)
    private Byte isAnswerCheck; // 답변 달렷는지 유무
}
