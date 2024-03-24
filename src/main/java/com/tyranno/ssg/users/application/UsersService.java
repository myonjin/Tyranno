package com.tyranno.ssg.users.application;

import com.tyranno.ssg.users.dto.LoginDto;
import com.tyranno.ssg.users.dto.SignUpDto;
import com.tyranno.ssg.users.dto.UserIdentifyDto;

public interface UsersService {

    void createUsers(SignUpDto signUpDto);

    String loginUsers(LoginDto loginDto);

    void checkLoginId(String loginId);
    String findLoginId(UserIdentifyDto userIdentifyDto);

    void changePassword(String password, String uuid);

}
