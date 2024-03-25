package com.tyranno.ssg.option.infrastructure;

import com.tyranno.ssg.option.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long>{


    List<Option> findByProductId(Long productId);

    @Query("SELECT o FROM Option o LEFT JOIN o.color c " +
                                     "LEFT JOIN o.size s " +
                                     "LEFT JOIN o.extra e " +
                                     "LEFT JOIN o.etc t " +
                                     "WHERE o.product.id = :productId")
    List<?> findname(@Param("productId") Long productId);


//    List<Option> findByProductId(Long productId);
//
//    List<String> findOptionAble(Long productId);
}
