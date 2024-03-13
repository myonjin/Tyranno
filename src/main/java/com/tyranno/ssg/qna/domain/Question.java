package com.tyranno.ssg.qna.domain;

import com.tyranno.ssg.auth.users.domain.Users;
import com.tyranno.ssg.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Q&A 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "title")
    private String title; // 제목

    @Column(name = "content", nullable = false)
    private String content; // 내용

    @Column(name = "rgdt", nullable = false)
    private LocalDateTime rgdt; // 작성일

    @Column(name = "modify_rgdt")
    private LocalDateTime modifyRgdt; // 수정일

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete; // 삭제 여부

    @Column(name = "is_private", nullable = false)
    private Boolean isPrivate; // 비밀 여부

    @Column(name = "parent_id")
    private long parentId; // 답변에 대한 질문 아이디

    @Column(name = "is_answer_check", nullable = false)
    private Boolean isAnswerCheck; // 답변 달렷는지 유무
}
