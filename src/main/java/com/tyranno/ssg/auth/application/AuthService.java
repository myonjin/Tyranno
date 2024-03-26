package com.tyranno.ssg.auth.application;

import com.tyranno.ssg.auth.dto.*;
import com.tyranno.ssg.users.dto.PasswordModifyDto;

public interface AuthService {

    void createUsers(SignUpDto signUpDto);

    String loginUsers(LoginDto loginDto);

    void checkLoginId(IdCheckDto idCheckDto);

    String findLoginId(UserIdentifyDto userIdentifyDto);

    void changePassword(PasswordChangeDto passwordChangeDto);

}
