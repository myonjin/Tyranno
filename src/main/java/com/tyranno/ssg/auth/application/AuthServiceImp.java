package com.tyranno.ssg.auth.application;

import com.tyranno.ssg.auth.dto.*;
import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.delivery.infrastructure.DeliveryRepository;
import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.security.JwtTokenProvider;
import com.tyranno.ssg.users.domain.MarketingInformation;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.dto.MarketingType;
import com.tyranno.ssg.users.infrastructure.MarketingInformationRepository;
import com.tyranno.ssg.users.infrastructure.MarketingRepository;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final UsersRepository usersRepository;
    private final MarketingRepository marketingRepository;
    private final MarketingInformationRepository marketingInformationRepository;
    private final DeliveryRepository deliveryRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional // 반복이 될수 있음. 모든 곳에서 붙이는건 생각해봐야함  이유가 명확해야함
    @Override
    public void createUsers(SignUpDto signUpDto) {
        //회원
        Users users = signUpDto.toUsersEntity();
        usersRepository.save(users);

        //마케팅
        for (MarketingType type : MarketingType.values()) {
            Byte isAgree = 99; // default : 99 - 비동의
            switch (type) {
                case SHINSEGAE:
                    isAgree = signUpDto.getShinsegaeMarketingAgree();
                    break;
                case SHINSEGAE_OPTION:
                    isAgree = signUpDto.getShinsegaeOptionAgree();
                    break;
                case SSG:
                    isAgree = signUpDto.getSsgMarketingAgree();
                    break;
            }

            MarketingInformation marketingInformation = MarketingInformation.builder()
                    .isAgree(isAgree)
                    .users(users)
                    .marketing(marketingRepository.findById(type.getId()).orElseThrow())
                    .build();

            marketingInformationRepository.save(marketingInformation);
        }

        // 배송지
        Delivery delivery = signUpDto.toDeliveryEntity(users);
        deliveryRepository.save(delivery);
    }

    @Override
    public void checkLoginId(IdCheckDto idCheckDto) {
        if (usersRepository.existsByLoginId(idCheckDto.getLoginId())) {
            throw new GlobalException(ResponseStatus.DUPLICATE_ID);
        }
    }

    @Override
    public String loginUsers(LoginDto loginDto) {
        Users users = usersRepository.findByLoginId(loginDto.getLoginId())
                .orElseThrow(() -> new GlobalException(ResponseStatus.FAILED_TO_LOGIN));

        if (bCryptPasswordEncoder.matches(loginDto.getPassword(), users.getPassword())) {
            return jwtTokenProvider.generateToken(users);
        } else throw new GlobalException(ResponseStatus.FAILED_TO_LOGIN);
    }


    @Override
    public String findLoginId(UserIdentifyDto userIdentifyDto) {
        Users users = usersRepository.findByNameAndPhoneNumberAndGenderAndBirth(
                userIdentifyDto.getName(), userIdentifyDto.getPhoneNumber(), userIdentifyDto.getGender(), userIdentifyDto.getBirth()
        ).orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
        return users.getLoginId(); //jpql
    }

    @Transactional
    @Override
    public void changePassword(PasswordChangeDto passwordChangeDto) {
        Users users = usersRepository.findByLoginId((passwordChangeDto.getLoginId()))
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
        ;
        usersRepository.save(passwordChangeDto.toEntity(users));
    }
}
