package com.tyranno.ssg.auth.sms.application;

import com.tyranno.ssg.auth.sms.infrastructure.SmsCertification;
import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.auth.sms.dto.SmsCertificationDto;
import com.tyranno.ssg.auth.sms.dto.SmsSendDto;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SmsServiceImp implements SmsService {
    private final DefaultMessageService messageService;
    private final String fromNumber;
    private final SmsCertification smsCertification;

    public SmsServiceImp(@Value("${COOLSMS.APIKEY}") String apiKey,
                         @Value("${COOLSMS.APISECRET}") String apiSecret,
                         @Value("${COOLSMS.FROMNUMBER}") String fromNumber, SmsCertification smsCertification,
                         UsersRepository usersRepository) {
        this.fromNumber = fromNumber;
        this.smsCertification = smsCertification;
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
    }

    private String createRandomNumber() {
        Random rand = new Random();
        String randomNum = "";
        for (int i = 0; i < 4; i++) {
            String random = Integer.toString(rand.nextInt(10));
            randomNum += random;
        }
        return randomNum;
    }

    @Override
    public SingleMessageSentResponse sendOne(SmsSendDto smsSendDto) {
        String randomNum = createRandomNumber();
        String receiverPhoneNum = smsSendDto.getPhoneNumber();

        Message message = new Message();
        message.setFrom(fromNumber);
        message.setTo(receiverPhoneNum);
        message.setText(String.format("[SSG] 본인확인 인증번호 [%s]를 입력해주세요.", randomNum));

        smsCertification.createSmsCertification(receiverPhoneNum, randomNum);

        return messageService.sendOne(new SingleMessageSendingRequest(message));
    }

    @Override
    public void verifySms(SmsCertificationDto SmsCertificationDto) {
        if (!isVerify(SmsCertificationDto)) {
            throw new GlobalException(ResponseStatus.NO_MATCH_CERTIFICATION_NUMBER);
        }
        smsCertification.deleteSmsCertification(SmsCertificationDto.getPhoneNumber());
    }

    private boolean isVerify(SmsCertificationDto requestDto) {
        return (smsCertification.hasKey(requestDto.getPhoneNumber()) &&
                smsCertification.getSmsCertification(requestDto.getPhoneNumber())
                        .equals(requestDto.getRandomNumber()));
        // 폰 넘버가 key값으로 redis에 있고 && redis에 저장된 인증번호와 이용자 입력번호가 일치한다면 true
    }


}
