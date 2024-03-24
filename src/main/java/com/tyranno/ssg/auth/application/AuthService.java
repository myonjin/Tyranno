package com.tyranno.ssg.auth.application;

import com.tyranno.ssg.auth.dto.UsersInfoDto;
import com.tyranno.ssg.auth.dto.UsersModifyDto;

public interface AuthService {

    void modifyShinsegaeMaketing(Byte isAgree, String uuid);
    void modifySsgMaketing(Byte isAgree, String uuid);
    void modifyUsers(UsersModifyDto usersModifyDto, String uuid);
    UsersInfoDto getUsersInfo(String uuid); // api 응답값 이상 - 확인 필요
    void resignUsers(String uuid);
}
