package com.tyranno.ssg.review.dto;

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
    private Map<String, Long> optionIds = new HashMap<>();
    private String orderNumber;
    private LocalDateTime orderDate;

    public ReviewPageDto(Long productId, String productName, String productThum, Map<String, Long> optionIds,
                         String orderNumber, LocalDateTime orderDate) {
        this.productId = productId;
        this.productName = productName;
        this.productThum = productThum;
        this.optionIds = optionIds;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
    }
}