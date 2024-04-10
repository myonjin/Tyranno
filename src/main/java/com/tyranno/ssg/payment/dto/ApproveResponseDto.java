package com.tyranno.ssg.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApproveResponseDto {
    private String aid; // 요청 고유 번호 - 승인/취소가 구분된 결제번호
    private String tid; // 결제 고유 번호 - 승인/취소가 동일한 결제번호
    private String cid; // 가맹점 코드
    private String partner_order_id;
    private String partner_user_id;
    private Amount amount;
    private String item_name;
    private int quantity;
    private LocalDateTime approved_at;
}
