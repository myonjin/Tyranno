package com.tyranno.ssg.review.infrastructure;

import com.tyranno.ssg.review.domain.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
