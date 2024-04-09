package com.tyranno.ssg.review.dto;

import com.tyranno.ssg.option.dto.OptionNamesDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ReviewPageDto {
    private Long productId;
    private String productName;
    private String productThum;
    private OptionNamesDto optionNamesDto;
    private String orderNumber;
    private LocalDateTime orderDate;

    @Builder
    public ReviewPageDto(Long productId, String productName, String productThum, OptionNamesDto optionNamesDto,
                         String orderNumber, LocalDateTime orderDate) {
        this.productId = productId;
        this.productName = productName;
        this.productThum = productThum;
        this.optionNamesDto = optionNamesDto;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
    }
}