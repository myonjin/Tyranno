package com.tyranno.ssg.users.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.users.domain.Marketing;
import com.tyranno.ssg.users.domain.MarketingInformation;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.dto.MarketingModifyDto;
import com.tyranno.ssg.users.dto.PasswordModifyDto;
import com.tyranno.ssg.users.dto.UsersInfoDto;
import com.tyranno.ssg.users.dto.UsersModifyDto;
import com.tyranno.ssg.users.infrastructure.MarketingInformationRepository;
import com.tyranno.ssg.users.infrastructure.MarketingRepository;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImp implements UsersService {
    private final UsersRepository usersRepository;
    private final MarketingRepository marketingRepository;
    private final MarketingInformationRepository marketingInformationRepository;

    public Users getUsers(String uuid) {
        return usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
    }


    @Transactional
    @Override
    public void modifyPassword(PasswordModifyDto passwordModifyDto, String uuid) {
        Users users = usersRepository.findByUuid((uuid))
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
        usersRepository.save(passwordModifyDto.toEntity(users));
    }

    @Transactional
    @Override
    public void modifyShinsegaeMaketing(MarketingModifyDto marketingModifyDto, String uuid) {
        Users users = getUsers(uuid);
        Marketing marketing = marketingRepository.findById(2L).orElseThrow();
        MarketingInformation marketingInformation = marketingInformationRepository.findByUsersAndMarketing(users, marketing)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_MARKETING));

        marketingInformationRepository.save(marketingModifyDto.toEntity(marketingInformation));
    }

    @Override
    public void modifySsgMaketing(MarketingModifyDto marketingModifyDto, String uuid) {
        Users users = getUsers(uuid);
        Marketing marketing = marketingRepository.findById(3L).orElseThrow();
        MarketingInformation marketingInformation = marketingInformationRepository.findByUsersAndMarketing(users, marketing)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_MARKETING));

        marketingInformationRepository.save(marketingModifyDto.toEntity(marketingInformation));
    }

    @Transactional
    @Override
    public void modifyUsers(UsersModifyDto usersModifyDto, String uuid) {
        Users users = getUsers(uuid);
        usersRepository.save(usersModifyDto.toEntity(users));
    }

    @Override
    public UsersInfoDto getUsersInfo(String uuid) {
        return usersRepository.findByUuid(uuid)
                .map(UsersInfoDto::FromEntity)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
    }

    @Transactional
    @Override
    public void resignUsers(String uuid) {
        Users users = getUsers(uuid);

        Users.builder()
                .loginId(users.getLoginId())
                .name(users.getName())
                .email(users.getEmail())
                .gender(users.getGender())
                .phoneNumber(users.getPhoneNumber())
                .birth(users.getBirth())
                .status(1) // 탈퇴
                .uuid(users.getUuid())
                .build();

        usersRepository.save(users);
    }


}
