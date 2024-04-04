package com.tyranno.ssg.auth.application;

import com.tyranno.ssg.auth.dto.*;
import com.tyranno.ssg.users.domain.Users;
import jakarta.transaction.Transactional;

public interface AuthService {
    void singUpUsers(SignUpDto signUpDto);
    String loginUsers(LoginDto loginDto);
    void addMarketingInformation(MarketingAgreeDto marketingAgreeDto, Users users);
    void checkLoginId(IdCheckDto idCheckDto);
    void checkEmail(EmailCheckDto emailCheckDto);
    String getLoginId(PhoneNumberDto phoneNumberDto);
    void changePassword(PasswordChangeDto passwordChangeDto);
    void checkPhoneNumber(PhoneNumberDto phoneNumberDto);
}
