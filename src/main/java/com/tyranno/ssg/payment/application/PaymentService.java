package com.tyranno.ssg.payment.application;

import com.tyranno.ssg.config.KakaoPayProperties;
import com.tyranno.ssg.payment.dto.ApproveRequestDto;
import com.tyranno.ssg.payment.dto.ApproveResponseDto;
import com.tyranno.ssg.payment.dto.ReadyRequestDto;
import com.tyranno.ssg.payment.dto.ReadyResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class PaymentService {
    public ReadyResponseDto getKakaoPayReady(ReadyRequestDto request) {
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(this.getReadyParameters(request), this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();
        ReadyResponseDto response = restTemplate.postForObject(
                KakaoPayProperties.readyUrl,
                requestEntity,
                ReadyResponseDto.class
        );
        return response;

    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();


        httpHeaders.set("Authorization", KakaoPayProperties.adminKey);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }

    private MultiValueMap<String, String> getReadyParameters(ReadyRequestDto request) {

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", KakaoPayProperties.cid);
        parameters.add("partner_order_id", request.getPartner_order_id());
        parameters.add("partner_user_id", request.getPartner_user_id());
        parameters.add("item_name", request.getItem_name());
        parameters.add("quantity", String.valueOf(request.getQuantity()));
        parameters.add("total_amount", String.valueOf(request.getTotal_amount()));
        parameters.add("tax_free_amount", String.valueOf(request.getTax_free_amount()));
        parameters.add("approval_url", request.getApproval_url());
        parameters.add("cancel_url", request.getCancel_url());
        parameters.add("fail_url", request.getCancel_url());

        return parameters;
    }

    private MultiValueMap<String, String> getApproveParameters(ApproveRequestDto request) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", KakaoPayProperties.cid);
        parameters.add("tid", request.getTid());
        parameters.add("partner_order_id", request.getPartner_order_id());
        parameters.add("partner_user_id", request.getPartner_user_id());
        parameters.add("pg_token", request.getPg_token());

        return parameters;
    }

    public ApproveResponseDto getKakaoPayApprove(ApproveRequestDto request) {
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(this.getApproveParameters(request), this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();

        ApproveResponseDto response = restTemplate.postForObject(
                KakaoPayProperties.approveUrl,
                requestEntity,
                ApproveResponseDto.class
        );
        return response;
    }
}
