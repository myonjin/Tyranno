package com.tyranno.ssg.recent.dto;

import com.tyranno.ssg.recent.domain.RecentViewedProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentViewedDto {

    private Long id;

    private Byte isView;

    @Builder
    public RecentViewedDto (Long id, Byte isView) {
        this.id = id;
        this.isView = isView;
    }

    public static RecentViewedDto FromEntity(RecentViewedProduct recentViewedProduct) {
        return RecentViewedDto.builder()
                .id(recentViewedProduct.getId())
                .isView(recentViewedProduct.getIsView())
                .build();
    }

}
