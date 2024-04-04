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
    private LocalDateTime createdAt;
    private Byte isView;

    @Builder
    public RecentViewedDto (Long id, Byte isView, LocalDateTime createdAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.isView = isView;
    }

    public static RecentViewedDto FromEntity(RecentViewedProduct recentViewedProduct) {
        return RecentViewedDto.builder()
                .id(recentViewedProduct.getId())
                .isView(recentViewedProduct.getIsView())
                .createdAt(recentViewedProduct.getCreatedAt())
                .build();
    }

}
