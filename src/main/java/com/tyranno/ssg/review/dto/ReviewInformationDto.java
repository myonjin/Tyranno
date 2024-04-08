package com.tyranno.ssg.review.dto;

import com.tyranno.ssg.option.dto.OptionNamesDto;
import com.tyranno.ssg.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewInformationDto {
    private Float rate;
    private LocalDateTime createdAt;
    private Long id;
    private String loginId;
    private String content;
    private String orderNumber;
    private OptionNamesDto optionNamesDto;
    private List<ReviewImageDto> reviewImageDtos;

    @Builder
    public ReviewInformationDto(Float rate, LocalDateTime createdAt, Long id, String loginId,
                                String content, String orderNumber,
                                OptionNamesDto optionNamesDto, List<ReviewImageDto> reviewImageDtos) {
        this.rate = rate;
        this.createdAt = createdAt;
        this.id = id;
        this.loginId = loginId;
        this.content = content;
        this.orderNumber = orderNumber;
        this.optionNamesDto = optionNamesDto;
        this.reviewImageDtos = reviewImageDtos;
    }
}
