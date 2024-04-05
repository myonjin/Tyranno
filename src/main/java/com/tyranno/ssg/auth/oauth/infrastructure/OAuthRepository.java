package com.tyranno.ssg.auth.oauth.infrastructure;

import com.tyranno.ssg.auth.oauth.domain.OAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthRepository extends JpaRepository<OAuth, Long> {
    Optional<OAuth> findByExternalId(Long externalId);
    boolean existsByUsersId(Long usersId);

}
