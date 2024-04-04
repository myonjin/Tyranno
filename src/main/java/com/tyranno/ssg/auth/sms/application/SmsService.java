package com.tyranno.ssg.auth.sms.application;

import com.tyranno.ssg.auth.sms.dto.SmsCertificationDto;
import com.tyranno.ssg.auth.sms.dto.SmsSendDto;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

public interface SmsService {
    SingleMessageSentResponse sendOne(SmsSendDto smsSendDto);

    void verifySms(SmsCertificationDto SmsCertificationDto);


}
