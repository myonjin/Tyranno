package com.tyranno.ssg.auth.application;

import com.tyranno.ssg.auth.dto.PasswordModifyDto;
import com.tyranno.ssg.auth.dto.UsersInfoDto;
import com.tyranno.ssg.auth.dto.UsersModifyDto;
import com.tyranno.ssg.users.domain.Users;
import jakarta.transaction.Transactional;

public interface AuthService {

    void modifyPassword(PasswordModifyDto passwordModifyDto, String uuid);

    void modifyShinsegaeMaketing(Byte isAgree, String uuid);

    void modifySsgMaketing(Byte isAgree, String uuid);

    void modifyUsers(UsersModifyDto usersModifyDto, String uuid);

    UsersInfoDto getUsersInfo(String uuid);

    void resignUsers(String uuid);

    Users getUsers(String uuid);
}
