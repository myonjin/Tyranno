package com.tyranno.ssg.payment.application;

import com.tyranno.ssg.payment.dto.ApproveResponseDto;
import com.tyranno.ssg.payment.dto.ReadyRequestDto;
import com.tyranno.ssg.payment.dto.ReadyResponseDto;
import com.tyranno.ssg.payment.dto.SuccessInfoDto;

public interface PaymentService {
    ReadyResponseDto kakaoPayReady(ReadyRequestDto request);

  //  SuccessInfoDto giveSuccessInfo(String id, String pgToken);

    ApproveResponseDto getKakaoPayApprove(SuccessInfoDto successInfoDto);
}
