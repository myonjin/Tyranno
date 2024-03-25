package com.tyranno.ssg.users.infrastructure;

import com.tyranno.ssg.users.domain.Marketing;
import com.tyranno.ssg.users.domain.MarketingInformation;
import com.tyranno.ssg.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketingInformationRepository extends JpaRepository<MarketingInformation, Long> {
    Optional<MarketingInformation> findByUsersAndMarketing(Users users, Marketing marketing);
}
