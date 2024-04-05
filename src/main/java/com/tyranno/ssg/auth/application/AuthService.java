package com.tyranno.ssg.auth.application;

import com.tyranno.ssg.auth.dto.*;
import com.tyranno.ssg.users.domain.Users;
import jakarta.transaction.Transactional;

public interface AuthService {
    // 기존 회원 여부 조회 (휴대폰 번호)
    String checkOAuthUsersByPhoneNum(PhoneNumberDto phoneNumberDto);

    @Transactional // 기존 소셜 회원 통합회원 연결
    void connectUsers(ConnectUsersDto connectUsersDto);

    void singUpUsers(SignUpDto signUpDto);
    String loginUsers(LoginDto loginDto);
    void addMarketingInformation(MarketingAgreeDto marketingAgreeDto, Users users);
    void checkLoginId(IdCheckDto idCheckDto);
    void checkEmail(EmailCheckDto emailCheckDto);
    String getLoginId(PhoneNumberDto phoneNumberDto);
    void changePassword(PasswordChangeDto passwordChangeDto);
    void checkPhoneNumber(PhoneNumberDto phoneNumberDto);
}
