package com.tyranno.ssg.auth.application;

import com.tyranno.ssg.auth.dto.*;
import com.tyranno.ssg.auth.oauth.infrastructure.OAuthRepository;
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
    private final OAuthRepository oauthRepository;

    @Override
    // 기존 회원 여부 조회 (휴대폰 번호)
    public String checkOAuthUsersByPhoneNum(PhoneNumberDto phoneNumberDto) {
        Users users = usersRepository.findByPhoneNumber(phoneNumberDto.getPhoneNumber())
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_SIGNUP));
        if(users.getIsRegistered() == 1) return "통합 회원입니다.";
        return oauthRepository.existsByUsers(users) ? "소셜 회원입니다." : "통합회원 여부가 false인데 oauth table이 없음";
    }

    @Transactional // 기존 소셜 회원 통합회원 연결
    @Override
    public void connectUsers(ConnectUsersDto connectUsersDto) {
        Users users = usersRepository.findByPhoneNumber(connectUsersDto.getPhoneNumber())
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));

        // logId, password, 통합회원 여부 적용
        usersRepository.save(connectUsersDto.toEntity(users));
    }

    @Transactional
    @Override
    public void singUpUsers(SignUpDto signUpDto) {
        //회원
        Users users = signUpDto.toUsersEntity();
        usersRepository.save(users);

        //마케팅
        MarketingAgreeDto marketingAgreeDto = new MarketingAgreeDto(
                signUpDto.getShinsegaeMarketingAgree(),
                signUpDto.getShinsegaeOptionAgree(),
                signUpDto.getSsgMarketingAgree());

        addMarketingInformation(marketingAgreeDto, users);

        //배송지
        Delivery delivery = signUpDto.toDeliveryEntity(users);
        deliveryRepository.save(delivery);
    }

    @Transactional
    @Override
    public void addMarketingInformation(MarketingAgreeDto marketingAgreeDto, Users users) {

        for (MarketingType type : MarketingType.values()) {

            Byte isAgree = switch (type) {
                case SHINSEGAE -> marketingAgreeDto.getShinsegaeMarketingAgree();
                case SHINSEGAE_OPTION -> marketingAgreeDto.getShinsegaeOptionAgree();
                case SSG -> marketingAgreeDto.getSsgMarketingAgree();
            }; // default : 99 - 비동의

            MarketingInformation marketingInformation = MarketingInformation.builder()
                    .isAgree(isAgree)
                    .users(users)
                    .marketing(marketingRepository.findById(type.getId()).orElseThrow())
                    .build();

            marketingInformationRepository.save(marketingInformation);
        }
    }

    @Override
    public void checkLoginId(IdCheckDto idCheckDto) {
        if (usersRepository.existsByLoginId(idCheckDto.getLoginId())) {
            throw new GlobalException(ResponseStatus.DUPLICATE_ID);
        }
    }

    @Override
    public void checkEmail(EmailCheckDto emailCheckDto) {
        if (usersRepository.existsByEmail(emailCheckDto.getEmail())) {
            throw new GlobalException(ResponseStatus.DUPLICATE_EMAIL);
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
    public String getLoginId(PhoneNumberDto phoneNumberDto) {
        Users users = usersRepository.findByPhoneNumber(phoneNumberDto.getPhoneNumber())
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
        // 소셜 회원일 경우
        if (users.getIsRegistered() == 0) throw new GlobalException(ResponseStatus.NO_REGISTER);

        return users.getLoginId();
    }

    @Transactional
    @Override
    public void changePassword(PasswordChangeDto passwordChangeDto) {
        Users users = usersRepository.findByLoginId((passwordChangeDto.getLoginId()))
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
        ;
        usersRepository.save(passwordChangeDto.toEntity(users));
    }

    @Override
    public void checkPhoneNumber(PhoneNumberDto phoneNumberDto) {
        if (usersRepository.existsByPhoneNumber(phoneNumberDto.getPhoneNumber())) {
            throw new GlobalException(ResponseStatus.DUPLICATED_USERS);
        }
    }
}
