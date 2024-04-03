package com.tyranno.ssg.text.application;

import com.tyranno.ssg.text.dto.SmsCertificationDto;
import com.tyranno.ssg.text.dto.SmsSendDto;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

public interface SmsService {
    SingleMessageSentResponse sendOne(SmsSendDto smsSendDto);

    void verifySms(SmsCertificationDto SmsCertificationDto);


}
