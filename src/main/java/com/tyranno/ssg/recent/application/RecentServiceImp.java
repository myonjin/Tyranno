package com.tyranno.ssg.recent.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.recent.dto.RecentViewedDto;
import com.tyranno.ssg.recent.infrastructure.RecentViewedRepository;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecentServiceImp implements RecentService {
    private final RecentViewedRepository recentRepository;
    private final UsersRepository usersRepository;
    @Override
    public RecentViewedDto getRecentByUser(String uuid) {

        return recentRepository.findByUsersId(usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS)).getId())
                .map(RecentViewedDto::FromEntity)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_PRODUCT)); // 에러가 아니라 비었다고 전달해야 할것 같은데
    }
}
