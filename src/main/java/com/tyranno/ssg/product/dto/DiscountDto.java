package com.tyranno.ssg.product.dto;

import com.tyranno.ssg.product.domain.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {
    private Integer discount;
    private LocalDateTime discountStart;
    private LocalDateTime discountEnd;
}
