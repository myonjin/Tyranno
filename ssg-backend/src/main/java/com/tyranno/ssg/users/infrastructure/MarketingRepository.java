package com.tyranno.ssg.users.infrastructure;

import com.tyranno.ssg.users.domain.Marketing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketingRepository extends JpaRepository<Marketing, Long> {
}
