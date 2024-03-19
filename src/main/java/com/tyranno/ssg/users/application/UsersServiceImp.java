package com.tyranno.ssg.users.application;

import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.dto.SignUpDto;
import com.tyranno.ssg.users.dto.UsersModifyDto;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceImp implements UsersService {
    private final UsersRepository usersRepository;

    @Transactional // 반복이 될수 있음. 모든 곳에서 붙이는건 생각해봐야함  이유가 명확해야함
    @Override
    public Users createUsers(SignUpDto signUpDto) {
        String generatedUuid = UUID.randomUUID().toString();

        Users users =  Users.builder()
                .loginId(signUpDto.getLoginId())
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .gender(signUpDto.getGender())
                .phoneNumber(signUpDto.getPhoneNumber())
                .birth(signUpDto.getBirth())
                .status(0) // 활동중
                .uuid(generatedUuid)
                .build();

        users.hashPassword(signUpDto.getPassword());
        usersRepository.save(users);

        return users;
    }

    @Transactional
    @Override
    public SignUpDto modifyUsersInfo(UsersModifyDto usersModifyDto) {
        validateModifyUsers(usersModifyDto);

        Users users = usersRepository.findByPhoneNumber(usersModifyDto.getPhoneNumber()).orElseThrow(// 찾았는데 null일 경우
                //            () -> new UsersExcetion(No_USERS)
        );
//        // 기존 entity에 있던 정보 + 새로 set 정보
//        users.setEmail(usersModifyDto.getEmail());
//        users.setPassword(usersModifyDto.getPassword());
//        users.setPhoneNumber(usersModifyDto.getPhoneNumber());

        return SignUpDto.builder()
                .loginId(users.getLoginId())
                .password(users.getPassword())
                .name(users.getName())
                .email(users.getEmail())
                .gender(users.getGender())
                .phoneNumber(users.getPhoneNumber())
                .birth(users.getBirth())
                //.status(users.getStatus())
                .build();
    }


    private void validateModifyUsers(UsersModifyDto usersModifyDto) {
    }

    @Transactional
    @Override
    public void modifyMaketing() {

    }

    @Override
    public SignUpDto getUsersInfo(String uuid) {
        return null;
    }

    @Transactional
    @Override
    public void resignUsers(String uuid) {

    }

    @Transactional // patch가 아니라 put 이기 때문에
    @Override
    public void modifyPassword() {

    }
}
