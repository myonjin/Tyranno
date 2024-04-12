package com.tyranno.ssg.auth.application;

import com.tyranno.ssg.auth.dto.*;
import com.tyranno.ssg.users.domain.Users;
import jakarta.transaction.Transactional;

public interface AuthService {
    // 기존 회원 여부 조회 (휴대폰 번호)
    UsersTypeInfoDto checkOAuthUsersByPhoneNum(PhoneNumberDto phoneNumberDto);
    // 기존 소셜 회원 통합회원 연결
    void connectUsers(ConnectUsersDto connectUsersDto);
    String singUpUsers(SignUpDto signUpDto);
    String loginUsers(LoginDto loginDto);
    void addMarketingInformation(MarketingAgreeDto marketingAgreeDto, Users users);
    void checkLoginId(String loginId);
    void checkEmail(String email);
    String getLoginId(PhoneNumberDto phoneNumberDto);
    void changePassword(PasswordChangeDto passwordChangeDto);
    void checkPhoneNumber(String phoneNumber);
}
