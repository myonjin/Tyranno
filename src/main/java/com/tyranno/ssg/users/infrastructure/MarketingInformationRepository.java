package com.tyranno.ssg.users.infrastructure;

import com.tyranno.ssg.users.domain.MarketingInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketingInformationRepository extends JpaRepository<MarketingInformation, Long> {
}
