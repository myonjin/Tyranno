package com.tyranno.ssg.payment.application;


import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.payment.domain.Payment;
import com.tyranno.ssg.payment.dto.ApproveResponseDto;
import com.tyranno.ssg.payment.dto.ReadyRequestDto;
import com.tyranno.ssg.payment.dto.ReadyResponseDto;
import com.tyranno.ssg.payment.dto.SuccessInfoDto;
import com.tyranno.ssg.payment.infrastructure.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaymentServiceImp implements PaymentService{

    private final PaymentRepository paymentRepository;
    static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드

    @Value("${kakaopay.secret-key}")
    private String secretKey;
    private final String USER = "point";

    @Override
    public ReadyResponseDto kakaoPayReady(ReadyRequestDto request) {
        String partnerOrderId = RandomStringUtils.randomAlphanumeric(10);
        String id = RandomStringUtils.randomAlphanumeric(10);

        HttpEntity<HashMap<String, String>> requestEntity = new HttpEntity<>(this.getReadyParameters(request, partnerOrderId, id), this.getHeaders());

        log.info(String.valueOf(requestEntity));

        RestTemplate restTemplate = new RestTemplate();
        ReadyResponseDto response = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/ready",
                requestEntity,
                ReadyResponseDto.class
        );

        log.info(String.valueOf(response));
        createPayment(partnerOrderId, USER + id, response.getTid());

        return response;
    }

    private void createPayment(String partnerOrderId, String partnerUserId, String tid) {
        Payment payment = Payment.builder()
                .partnerOrderId(partnerOrderId)
                .partnerUserId(partnerUserId)
                .tid(tid)
                .build();
        paymentRepository.save(payment);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "SECRET_KEY " + secretKey);
        httpHeaders.set("Content-type", "application/json");

        return httpHeaders;
    }

    private HashMap<String, String> getReadyParameters(ReadyRequestDto request, String partnerOrderId, String id) {
        log.info("getReadyParameters id: "+id);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("cid", cid);
        parameters.put("partner_order_id", partnerOrderId);
        parameters.put("partner_user_id", USER + id);
        parameters.put("item_name", request.getItem_name());
        parameters.put("quantity", "1");
        parameters.put("total_amount", String.valueOf(request.getTotal_amount()));
        parameters.put("tax_free_amount", "0");
        parameters.put("approval_url", "https://tyrannoback.com/api/v1/pay/success/" + id);
        parameters.put("cancel_url", "https://tyrannoback.com/api/v1/pay/cancel");
        parameters.put("fail_url", "https://tyrannoback.com/api/v1/pay/fail");
//        parameters.put("approval_url", "http://localhost:8080/api/v1/pay/success/" + id);
//        parameters.put("cancel_url", "http://localhost:8080/api/v1/pay/cancel");
//        parameters.put("fail_url", "http://localhost:8080/api/v1/pay/fail");

        return parameters;
    }
//    @Override
//    public SuccessInfoDto giveSuccessInfo(String id, String pgToken){
//        return new SuccessInfoDto(id, pgToken);
//    }
    @Override
    public ApproveResponseDto getKakaoPayApprove(SuccessInfoDto successInfoDto) {
        HttpEntity<HashMap<String, String>> requestEntity = new HttpEntity<>(this.getApproveParameters(successInfoDto.getId(), successInfoDto.getPgToken()), this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();

        ApproveResponseDto response = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/approve",
                requestEntity,
                ApproveResponseDto.class
        );
        return response;
    }

    private Payment getPayment(String id) {
        log.info("getPayment: "+USER + id);
        return paymentRepository.findByPartnerUserId(USER + id)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PAYMENT));
    }

    private HashMap<String, String> getApproveParameters(String id, String pgToken) {
        Payment payment = getPayment(id);

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("cid", cid);
        parameters.put("tid", payment.getTid());
        parameters.put("partner_order_id", payment.getPartnerOrderId());
        parameters.put("partner_user_id", payment.getPartnerUserId());
        parameters.put("pg_token", pgToken);

        return parameters;
    }
}
