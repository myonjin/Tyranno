package com.tyranno.ssg.auth.application;

import com.tyranno.ssg.auth.dto.*;

public interface AuthService {
    void createUsers(SignUpDto signUpDto);
    String loginUsers(LoginDto loginDto);
    void checkLoginId(IdCheckDto idCheckDto);
    void checkEmail(EmailCheckDto emailCheckDto);
    String getLoginId(PhoneNumberDto phoneNumberDto);
    void changePassword(PasswordChangeDto passwordChangeDto);
    void checkPhoneNumber(PhoneNumberDto phoneNumberDto);
}
