package com.tyranno.ssg.users.infrastructure;

import com.tyranno.ssg.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUuid(String uuid);

    Optional<Users> findByLoginId(String loginId);

    Optional<Users> findByPhoneNumber(String phoneNumber);

    Optional<Users> findByUsername(String username);
}
