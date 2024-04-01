package com.tyranno.ssg.text;

import net.nurigo.sdk.message.response.SingleMessageSentResponse;

public interface SmsService {
    SingleMessageSentResponse sendOne(SmsSendDto smsSendDto);

    void verifySms(SmsCertificationDto SmsCertificationDto);


}
