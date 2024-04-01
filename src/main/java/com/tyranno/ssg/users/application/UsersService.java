package com.tyranno.ssg.users.application;

import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.dto.*;


public interface UsersService {

    String getUserName(String uuid);
    void modifyPassword(PasswordModifyDto passwordModifyDto, String uuid);
    void modifyMarketing(MarketingModifyDto marketingModifyDto, MarketingType marketingType, String uuid);

    void modifyUsers(UsersModifyDto usersModifyDto, String uuid);

    UsersInfoDto getUsersInfo(String uuid);

    void resignUsers(String uuid);

    Users getUsers(String uuid);
}
