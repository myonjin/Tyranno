package com.tyranno.ssg.text;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
//@RequestMapping("/api/v1/users")
public class TextController {

    private final MessageService messageService;

    //인증번호 발송
    // @PostMapping("/find_id")
    public ResponseEntity<?> sendSms(@RequestBody SmsCertificationDto requestDto) {
        messageService.sendSMS(requestDto.getPhoneNumber());
        return new ResponseEntity<>("findid", HttpStatus.OK);
    }

    //인증번호 확인
    //@PostMapping("/find_pw")
    public ResponseEntity<?> SmsVerification(@RequestBody SmsCertificationDto requestDto) {
        messageService.verifySms(requestDto);
        return new ResponseEntity<>("fidnpw", HttpStatus.OK);
    }
}