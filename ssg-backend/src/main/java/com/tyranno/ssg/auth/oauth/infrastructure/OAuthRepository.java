package com.tyranno.ssg.auth.oauth.infrastructure;

import com.tyranno.ssg.auth.oauth.domain.OAuth;
import com.tyranno.ssg.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthRepository extends JpaRepository<OAuth, Long> {
    OAuth findByExternalId(Long externalId);

    //boolean existsByExternalId(Long externalId);
    boolean existsByUsers(Users users);
}
