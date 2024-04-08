package com.tyranno.ssg.payment.application;


import com.tyranno.ssg.payment.dto.ApproveRequestDto;
import com.tyranno.ssg.payment.dto.ApproveResponseDto;
import com.tyranno.ssg.payment.dto.ReadyRequestDto;
import com.tyranno.ssg.payment.dto.ReadyResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaymentService {

    static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드

    @Value("${kakaopay.secret-key}")
    private String secretKey;


    public ReadyResponseDto kakaoPayReady(ReadyRequestDto request) {
        HttpEntity<HashMap<String, String>> requestEntity = new HttpEntity<>(this.getReadyParameters(request), this.getHeaders());

        log.info(String.valueOf(requestEntity));
        RestTemplate restTemplate = new RestTemplate();
        ReadyResponseDto response = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/ready",
                requestEntity,
                ReadyResponseDto.class
        );

        log.info(String.valueOf(response));

        return response;

    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "SECRET_KEY " + secretKey);
        httpHeaders.set("Content-type", "application/json");

        return httpHeaders;
    }

    private HashMap<String, String> getReadyParameters(ReadyRequestDto request) {

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("cid", cid);
        parameters.put("partner_order_id", request.getPartner_order_id());
        parameters.put("partner_user_id", request.getPartner_user_id());
        parameters.put("item_name", request.getItem_name());
        parameters.put("quantity", String.valueOf(request.getQuantity()));
        parameters.put("total_amount", String.valueOf(request.getTotal_amount()));
        parameters.put("tax_free_amount", String.valueOf(request.getTax_free_amount()));
        parameters.put("approval_url", request.getApproval_url());
        parameters.put("cancel_url", request.getCancel_url());
        parameters.put("fail_url", request.getCancel_url());
//        parameters.add("approval_url", "http://localhost:8080/api/v1/pay/success"); // 성공 시 redirect url
//        parameters.add("cancel_url", "http://localhost:8080/api/v1/pay/cancel"); // 취소 시 redirect url
//        parameters.add("fail_url", "http://localhost:8080/api/v1/pay/fail"); // 실패 시 redirect url

        return parameters;
    }

    public ApproveResponseDto getKakaoPayApprove(ApproveRequestDto request) {
        HttpEntity<HashMap<String, String>> requestEntity = new HttpEntity<>(this.getApproveParameters(request), this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();

        ApproveResponseDto response = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/approve",
                requestEntity,
                ApproveResponseDto.class
        );
        return response;
    }

    private HashMap<String, String> getApproveParameters(ApproveRequestDto request) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("cid", cid);
        parameters.put("tid", request.getTid());
        parameters.put("partner_order_id", request.getPartner_order_id());
        parameters.put("partner_user_id", request.getPartner_user_id());
        parameters.put("pg_token", request.getPg_token());

        return parameters;
    }
}
