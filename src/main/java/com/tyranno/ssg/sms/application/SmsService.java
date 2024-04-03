package com.tyranno.ssg.sms.application;

import com.tyranno.ssg.sms.dto.SmsCertificationDto;
import com.tyranno.ssg.sms.dto.SmsSendDto;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

public interface SmsService {
    SingleMessageSentResponse sendOne(SmsSendDto smsSendDto);

    void verifySms(SmsCertificationDto SmsCertificationDto);


}
