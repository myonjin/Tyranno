//package com.tyranno.ssg.text;
//
//import net.nurigo.sdk.NurigoApp;
//import net.nurigo.sdk.message.model.Message;
//import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
//import net.nurigo.sdk.message.response.SingleMessageSentResponse;
//import net.nurigo.sdk.message.service.DefaultMessageService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SmsServiceImp implements SmsService {
//    private final DefaultMessageService messageService;
//    private final String fromNumber;
//
//    public SmsServiceImp(@Value("${COOLSMS.APIKEY}") String apiKey,
//                         @Value("${COOLSMS.APISECRET}") String apiSecret,
//                         @Value("${COOLSMS.FROMNUMBER}") String fromNumber) {
//        this.fromNumber = fromNumber;
//        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
//    }
//
//    @Override
//    public SingleMessageSentResponse sendOne(SmsRequestDto smsRequestDto) {
//        Message message = new Message();
//        message.setFrom(fromNumber);
//        message.setTo(smsRequestDto.getPhoneNumber());
//        message.setText("[SSG] 인증번호를 입력해주세요.");
//        return messageService.sendOne(new SingleMessageSendingRequest(message));
//    }
//}
