package com.tyranno.ssg.review.infrastructure;

import com.tyranno.ssg.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
//    Optional<Review> findById(Long reviewId);
}
