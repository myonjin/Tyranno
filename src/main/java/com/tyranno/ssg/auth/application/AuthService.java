package com.tyranno.ssg.auth.application;

import com.tyranno.ssg.auth.dto.LoginDto;
import com.tyranno.ssg.auth.dto.PasswordModifyDto;
import com.tyranno.ssg.auth.dto.SignUpDto;
import com.tyranno.ssg.auth.dto.UserIdentifyDto;

public interface AuthService {

    void createUsers(SignUpDto signUpDto);

    String loginUsers(LoginDto loginDto);

    void checkLoginId(String loginId);

    String findLoginId(UserIdentifyDto userIdentifyDto);

    void changePassword(PasswordModifyDto passwordModifyDto, String uuid);

}
