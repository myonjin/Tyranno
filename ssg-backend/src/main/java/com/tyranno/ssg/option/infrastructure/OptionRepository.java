package com.tyranno.ssg.option.infrastructure;

import com.tyranno.ssg.option.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, Long> {

    @Query("SELECT o FROM Option o LEFT JOIN o.color c " +
            "LEFT JOIN o.size s " +
            "LEFT JOIN o.extra e " +
            "LEFT JOIN o.etc t " +
            "WHERE o.product.id = :productId")
    Optional<List<?>> findName(@Param("productId") Long productId);

    Optional<List<Option>> findAllByProductId(@Param("productId") Long productId);

    Optional<Option> findByProductId(Long productId);


}
