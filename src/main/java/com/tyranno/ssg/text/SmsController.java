package com.tyranno.ssg.text;

import com.tyranno.ssg.global.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "문자인증", description = "Text API")
@RequestMapping("/api/v1/auth/text")
public class SmsController {

    private final SmsService smsService;

    // 인증번호 전송
    @Operation(summary = "인증번호 전송", description = "인증번호 4자리를 전송한다.")
    @PostMapping("/send")
    public SingleMessageSentResponse sendOne(@RequestBody SmsSendDto smsSendDto) {
        return smsService.sendOne(smsSendDto);
    }

    // 인증번호 확인
    @Operation(summary = "인증번호 확인", description = "문자 인증번호가 맞는지 확인한다.")
    @PostMapping("/verify")
    public ResponseEntity<?> verifySms(@RequestBody SmsCertificationDto requestDto) {
        smsService.verifySms(requestDto);
        return new ResponseEntity<>("문자인증에 성공하였습니다.");
    }
}
