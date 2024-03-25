//package com.tyranno.ssg.text;
//
//import lombok.RequiredArgsConstructor;
//import net.nurigo.sdk.message.service.MessageService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/v1/users")
//public class TextController {
//    private final MessageService messageService;
//
//    //인증번호 발송
//    @PostMapping("/send")
//    public ResponseEntity<String> sendSms(@RequestBody SmsRequestDto requestDto) {
//        try {
//            String response = messageService.sendSMS(requestDto.getPhoneNumber());
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("문자 전송에 실패하였습니다.");
//        }
//    }
//
//    //인증번호 확인
//    @PostMapping("/verify")
//    public ResponseEntity<String> verifySms(@RequestBody SmsCertificationDto requestDto) {
//        try {
//            String response = messageService.verifySms(requestDto);
//            return ResponseEntity.ok(response);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("인증 검증 중 오류가 발생하였습니다.");
//        }
//    }
//}
