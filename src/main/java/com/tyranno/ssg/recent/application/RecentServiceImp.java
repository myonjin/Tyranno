package com.tyranno.ssg.recent.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.recent.dto.RecentViewedDto;
import com.tyranno.ssg.recent.infrastructure.RecentViewedRepository;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecentServiceImp implements RecentService{
    private final RecentViewedRepository recentRepository;
    private final UsersRepository usersRepository;
    @Override
    public RecentViewedDto getRecentByUser(String uuid) {
        return recentRepository.findByUsersId(getUsers(uuid).getId())
                .map(RecentViewedDto::FromEntity)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCT));
    }

    public Users getUsers(String uuid) {
        return usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
    }

}
