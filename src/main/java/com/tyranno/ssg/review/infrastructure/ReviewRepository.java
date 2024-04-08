package com.tyranno.ssg.review.infrastructure;

import com.tyranno.ssg.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
