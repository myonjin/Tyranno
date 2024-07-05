package com.tyranno.ssg.question.domain;

import com.tyranno.ssg.global.GlobalTime;
import com.tyranno.ssg.product.domain.Product;
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
public class Question extends GlobalTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Q&A 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private String title; // 제목

    @Column(nullable = false)
    private String content; // 내용

    @Column(nullable = false)
    private Byte isDelete; // 삭제 여부

    @Column(nullable = false)
    private Byte isPrivate; // 비밀 여부

    private Long parentId; // 답변에 대한 질문 아이디

    @Column(nullable = false)
    private Byte isAnswerCheck; // 답변 달렷는지 유무
}
