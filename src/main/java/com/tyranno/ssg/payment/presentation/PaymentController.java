package com.tyranno.ssg.payment.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "결제", description = "Pay API")
@RequestMapping("/api/v1/pay")
public class PaymentController {

    @Operation(summary = "카카오페이 결제", description = "카카오페이 결제를 한다.")
    @GetMapping("/approve")
    public ResponseEntity<ApiResponse> getPgToken(@RequestParam("pg_token") String pgToken) {
        return ResponseEntity.ok(ApiResponse.success(SUCCESS_GET_PGTOKEN.getMessage(), pgToken));
    }
}
