package com.tyranno.ssg.recent.dto;

import com.tyranno.ssg.recent.domain.RecentViewedProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RecentViewedDto {

    private Long id;
    private Long productId;

    @Builder
    public RecentViewedDto (Long id, Long productId) {
        this.id = id;

        this.productId = productId;
    }

    public static RecentViewedDto FromEntity(RecentViewedProduct recentViewedProduct) {
        return RecentViewedDto.builder()
                .id(recentViewedProduct.getId())
                .productId(recentViewedProduct.getProduct().getId())
                .build();
    }

}
