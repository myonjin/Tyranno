package com.tyranno.ssg.recent.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecentSearchHistory is a Querydsl query type for RecentSearchHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecentSearchHistory extends EntityPathBase<RecentSearchHistory> {

    private static final long serialVersionUID = 1989598611L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecentSearchHistory recentSearchHistory = new QRecentSearchHistory("recentSearchHistory");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath searchWord = createString("searchWord");

    public final com.tyranno.ssg.users.domain.QUsers users;

    public QRecentSearchHistory(String variable) {
        this(RecentSearchHistory.class, forVariable(variable), INITS);
    }

    public QRecentSearchHistory(Path<? extends RecentSearchHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecentSearchHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecentSearchHistory(PathMetadata metadata, PathInits inits) {
        this(RecentSearchHistory.class, metadata, inits);
    }

    public QRecentSearchHistory(Class<? extends RecentSearchHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new com.tyranno.ssg.users.domain.QUsers(forProperty("users")) : null;
    }

}

