package com.tyranno.ssg.payment.application;

import com.tyranno.ssg.payment.dto.ApproveResponseDto;
import com.tyranno.ssg.payment.dto.ReadyRequestDto;
import com.tyranno.ssg.payment.dto.ReadyResponseDto;

public interface PaymentService {
    ReadyResponseDto kakaoPayReady(ReadyRequestDto request);

    ApproveResponseDto getKakaoPayApprove(String id, String pgToken);
}
