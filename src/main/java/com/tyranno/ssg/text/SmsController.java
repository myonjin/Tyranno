package com.tyranno.ssg.text;

import com.tyranno.ssg.global.ResponseEntity;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class SmsController {

    private final SmsService smsService;

    // 인증번호 전송
    @PostMapping("/send")
    public SingleMessageSentResponse sendOne(@RequestBody SmsSendDto smsSendDto) {
        return smsService.sendOne(smsSendDto);
    }

    // 인증번호 확인
    @PostMapping("/verify")
    public ResponseEntity<?> verifySms(@RequestBody SmsCertificationDto requestDto) {
        smsService.verifySms(requestDto);
        return new ResponseEntity<>("문자인증에 성공하였습니다.");
    }
}
