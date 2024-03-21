package com.tyranno.ssg.question.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = 792152207L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final com.tyranno.ssg.global.QGlobalTime _super = new com.tyranno.ssg.global.QGlobalTime(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> isAnswerCheck = createNumber("isAnswerCheck", Byte.class);

    public final NumberPath<Byte> isDelete = createNumber("isDelete", Byte.class);

    public final NumberPath<Byte> isPrivate = createNumber("isPrivate", Byte.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final com.tyranno.ssg.product.domain.QProduct product;

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.tyranno.ssg.users.domain.QUsers users;

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.tyranno.ssg.product.domain.QProduct(forProperty("product")) : null;
        this.users = inits.isInitialized("users") ? new com.tyranno.ssg.users.domain.QUsers(forProperty("users")) : null;
    }

}

