package com.tyranno.ssg.payment.presentation;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.payment.application.PaymentService;
import com.tyranno.ssg.payment.dto.ApproveResponseDto;
import com.tyranno.ssg.payment.dto.ReadyRequestDto;
import com.tyranno.ssg.payment.dto.ReadyResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "결제", description = "Pay API")
@RequestMapping("/api/v1/pay")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "카카오페이 결제", description = "카카오페이 결제를 한다.")
    @PostMapping("/ready")
    public ReadyResponseDto readyToKakaoPay(@RequestBody ReadyRequestDto readyRequestDto) {

        return paymentService.kakaoPayReady(readyRequestDto);
    }

    /**
     * 결제 성공
     */
    @GetMapping("/success")
    public ResponseEntity<ApproveResponseDto> afterPayRequest(@RequestParam("pg_token") String pgToken) {

        //  ApproveResponseDto kakaoApprove = paymentService.getKakaoPayApprove(pgToken);
        return null;
        //  return new ResponseEntity<>(kakaoApprove);
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/cancel")
    public void cancel() {
        throw new GlobalException(ResponseStatus.PAY_CANCEL);
    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public void fail() {
        throw new GlobalException(ResponseStatus.PAY_FAILED);
    }
}
