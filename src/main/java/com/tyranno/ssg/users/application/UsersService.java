package com.tyranno.ssg.users.application;

import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.dto.UsersInfoDto;
import com.tyranno.ssg.users.dto.UsersModifyDto;


public interface UsersService {

    void modifyShinsegaeMaketing(Byte isAgree, String uuid);

    void modifySsgMaketing(Byte isAgree, String uuid);

    void modifyUsers(UsersModifyDto usersModifyDto, String uuid);

    UsersInfoDto getUsersInfo(String uuid);

    void resignUsers(String uuid);

    Users getUsers(String uuid);
}
