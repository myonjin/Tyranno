package com.tyranno.ssg.auth.application;

import com.tyranno.ssg.auth.dto.LoginDto;
import com.tyranno.ssg.auth.dto.PasswordModifyDto;
import com.tyranno.ssg.auth.dto.SignUpDto;
import com.tyranno.ssg.auth.dto.UserIdentifyDto;
import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.delivery.infrastructure.DeliveryRepository;
import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.security.JwtTokenProvider;
import com.tyranno.ssg.users.domain.MarketingInformation;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.MarketingInformationRepository;
import com.tyranno.ssg.users.infrastructure.MarketingRepository;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        String generatedUuid = UUID.randomUUID().toString();

        Users users = Users.builder()
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

        //마케팅
        MarketingInformation marketingInformation1 = MarketingInformation.builder()
                .isAgree(signUpDto.getShinsegaeMarketingAgree())
                .users(users)
                .marketing(marketingRepository.findById(1L).orElseThrow())
                .build(); // 아이디를 받거나 이넘으로 받거나

        marketingInformationRepository.save(marketingInformation1);

        MarketingInformation marketingInformation2 = MarketingInformation.builder()
                .isAgree(signUpDto.getShinsegaeOptionAgree())
                .users(users)
                .marketing(marketingRepository.findById(2L).orElseThrow())
                .build();

        marketingInformationRepository.save(marketingInformation2);

        MarketingInformation marketingInformation3 = MarketingInformation.builder()
                .isAgree(signUpDto.getSsgMarketingAgree())
                .users(users)
                .marketing(marketingRepository.findById(3L).orElseThrow())
                .build();

        marketingInformationRepository.save(marketingInformation3);

        // 배송지
        Delivery delivery = Delivery.builder()
                .users(users)
                .isBaseDelivery((byte) 11)
                .deliveryName(signUpDto.getName())
                .zipCode(signUpDto.getZipCode())
                .deliveryBase(signUpDto.getDeliveryBase())
                .deliveryDetail(signUpDto.getDeliveryDetail())
                .receiverName(signUpDto.getName())
                .phoneNumber(signUpDto.getPhoneNumber())
                .build();

        deliveryRepository.save(delivery);
    }

    @Override
    public void checkLoginId(String loginId) {
        if (usersRepository.existsByLoginId(loginId)) {
            throw new GlobalException(ResponseStatus.DUPLICATE_ID);
        }
    }

    @Override
    public String loginUsers(LoginDto loginDto) {
        Users users = usersRepository.findByLoginId(loginDto.getLoginId())
                .orElseThrow(() -> new GlobalException(ResponseStatus.FAILED_TO_LOGIN_ID));

        if (bCryptPasswordEncoder.matches(loginDto.getPassword(), users.getPassword())) {
            return jwtTokenProvider.generateToken(users);
        } else throw new GlobalException(ResponseStatus.FAILED_TO_LOGIN_PW);
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
    public void changePassword(PasswordModifyDto passwordModifyDto, String uuid) {
        Users users = usersRepository.findByUuid((uuid))
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
        users.hashPassword(passwordModifyDto.getNewPassword());
    }
<<<<<<< Updated upstream

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
=======
>>>>>>> Stashed changes
}
