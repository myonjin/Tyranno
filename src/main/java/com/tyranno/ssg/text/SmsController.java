//package com.tyranno.ssg.text;
//
//import net.nurigo.sdk.message.response.SingleMessageSentResponse;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/users")
//public class SmsController {
//    private final SmsService smsService;
//
//    public SmsController(SmsService smsService) {
//        this.smsService = smsService;
//    }
//
//    @PostMapping("/send")
//    public SingleMessageSentResponse sendOne(@RequestBody SmsRequestDto smsRequestDto) {
//        return smsService.sendOne(smsRequestDto);
//    }
//}
