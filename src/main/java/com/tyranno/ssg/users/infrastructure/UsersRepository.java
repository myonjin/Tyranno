package com.tyranno.ssg.users.infrastructure;

import com.tyranno.ssg.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByLoginId(String loginId);

    Optional<Users> findByUuid(String uuid);

    Optional<Users> findByLoginId(String loginId);

    Optional<Users> findByNameAndPhoneNumberAndGenderAndBirth(String name, String phoneNumber, Byte gender, LocalDate date);
}
