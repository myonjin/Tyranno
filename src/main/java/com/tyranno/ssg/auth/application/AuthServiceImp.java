package com.tyranno.ssg.auth.application;

import com.tyranno.ssg.auth.dto.PasswordModifyDto;
import com.tyranno.ssg.auth.dto.UsersInfoDto;
import com.tyranno.ssg.auth.dto.UsersModifyDto;
import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.users.domain.Marketing;
import com.tyranno.ssg.users.domain.MarketingInformation;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.MarketingInformationRepository;
import com.tyranno.ssg.users.infrastructure.MarketingRepository;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{
    private final UsersRepository usersRepository;
    private final MarketingRepository marketingRepository;
    private final MarketingInformationRepository marketingInformationRepository;



    @Transactional
    @Override
    public void modifyPassword(PasswordModifyDto passwordModifyDto, String uuid) {
        Users users = getUsers(uuid);;
        users.hashPassword(passwordModifyDto.getNewPassword());
    }

    @Transactional
    @Override
    public void modifyShinsegaeMaketing(Byte isAgree, String uuid) {
        if (isAgree != 11 && isAgree != 99){
            throw new GlobalException(ResponseStatus.NO_FORMATING);
        }
        Users users = getUsers(uuid);
//        try{
//        Users users = getUsers(uuid);
//        }
//        catch (Exception e) {
//            throw new GlobalException(ResponseStatus.NO_FORMATING);
//        }
        Marketing marketing = marketingRepository.findById(2L).orElseThrow();
        MarketingInformation marketingInformation = marketingInformationRepository.findByUsersAndMarketing(users, marketing)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_MARKETING));
        marketingInformation.updateIsAgree(isAgree);

    }

    @Transactional
    @Override
    public void modifySsgMaketing(Byte isAgree, String uuid) {
        Users users = getUsers(uuid);
        Marketing marketing = marketingRepository.findById(3L).orElseThrow();
        MarketingInformation marketingInformation = marketingInformationRepository.findByUsersAndMarketing(users, marketing)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_MARKETING));

        marketingInformation.updateIsAgree(isAgree);
    }

    @Transactional
    @Override
    public void modifyUsers(UsersModifyDto usersModifyDto, String uuid) {
        Users users = getUsers(uuid);
        users.hashPassword(usersModifyDto.getPassword());
        users.modifyInfo(usersModifyDto.getPhoneNumber(), usersModifyDto.getEmail());
    }

    @Override
    public UsersInfoDto getUsersInfo(String uuid) {
        Users users = getUsers(uuid);
        return UsersInfoDto.builder()
                .loginId(users.getLoginId())
                .name(users.getName())
                .phoneNumber(users.getPhoneNumber())
                .email(users.getEmail())
                .build();
    }

    @Transactional
    @Override
    public void resignUsers(String uuid) {
        Users users = getUsers(uuid);
        users.resign();
    }
    public Users getUsers(String uuid){
        return usersRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
    }
}
