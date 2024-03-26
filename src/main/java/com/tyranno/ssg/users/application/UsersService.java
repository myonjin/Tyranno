package com.tyranno.ssg.users.application;

import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.dto.MarketingModifyDto;
import com.tyranno.ssg.users.dto.PasswordModifyDto;
import com.tyranno.ssg.users.dto.UsersInfoDto;
import com.tyranno.ssg.users.dto.UsersModifyDto;


public interface UsersService {

    void modifyPassword(PasswordModifyDto passwordModifyDto, String uuid);
    void modifyShinsegaeMaketing(MarketingModifyDto marketingModifyDto, String uuid);

    void modifySsgMaketing(MarketingModifyDto marketingModifyDto, String uuid);

    void modifyUsers(UsersModifyDto usersModifyDto, String uuid);

    UsersInfoDto getUsersInfo(String uuid);

    void resignUsers(String uuid);

    Users getUsers(String uuid);
}
