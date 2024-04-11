package com.tyranno.ssg.users.infrastructure;

import com.tyranno.ssg.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByLoginId(String loginId);
    boolean existsByPhoneNumber(String phoneNumber);
   // boolean existsByNameAndEmail(String name, String email);
    boolean existsByEmail(String email);
    Optional<Users> findByUuid(String uuid);
    Optional<Users> findByLoginId(String loginId);
    Optional<Users> findByPhoneNumber(String phoneNumber);
    Optional<Users> findByNameAndEmail(String name, String email);
}
