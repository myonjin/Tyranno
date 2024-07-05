package com.tyranno.ssg.search.infrastructure;

import com.tyranno.ssg.search.domain.Search;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Search, Long> {
}
