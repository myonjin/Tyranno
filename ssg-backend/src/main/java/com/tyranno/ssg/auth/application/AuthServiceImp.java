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

import java.util.Optional;

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
    public UsersTypeInfoDto checkOAuthUsersByPhoneNum(PhoneNumberDto phoneNumberDto) {
        Optional<Users> optionalUsers = usersRepository.findByPhoneNumber(phoneNumberDto.getPhoneNumber());
        // 비회원
        if (optionalUsers.isEmpty()) {
            return new UsersTypeInfoDto(UsersType.NON_USERS.getCode(), UsersType.NON_USERS.getDescription());
        }
        Users users = optionalUsers.get();
        if (users.getIsIntegrated() == 1) { // 통합회원
            return new UsersTypeInfoDto(UsersType.INTEGRATED_USERS.getCode(), UsersType.INTEGRATED_USERS.getDescription());
        } else if (oauthRepository.existsByUsers(users)) { // 소셜회원
            return new UsersTypeInfoDto(UsersType.KAKAO_USERS.getCode(), UsersType.KAKAO_USERS.getDescription());
        } else { // 회원정보가 있는데 둘다 해당되지 않을 때
            throw new GlobalException(ResponseStatus.NO_EXIST_USERS_TYPE);
        }
    }

    @Transactional // 기존 소셜 회원 통합회원 연결
    @Override
    public void connectUsers(ConnectUsersDto connectUsersDto) {
        // 아이디 중복 시 회원 생성 막음
        checkLoginId(connectUsersDto.getLoginId());

        Users users = usersRepository.findByPhoneNumber(connectUsersDto.getPhoneNumber())
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));

        // logId, password, isIntegrated 적용
        usersRepository.save(connectUsersDto.toEntity(users));
    }

    @Transactional
    @Override
    public String singUpUsers(SignUpDto signUpDto) {
        // 아이디, 휴대폰번호, 이메일 중복 방지
        checkLoginId(signUpDto.getLoginId());

        Optional<Users> optionalUsers = usersRepository.findByPhoneNumber(signUpDto.getPhoneNumber());

        if (optionalUsers.isPresent()) { // 소셜회원이 통합회원가입 하는 경우
            // logId, password, 통합회원 여부 적용
            usersRepository.save(signUpDto.connctUsers(optionalUsers.get()));
            return "기존 소셜회원, 통합회원 연결하였습니다.";

        } else { // 비회원이 통합회원가입 하는 경우
            checkEmail(signUpDto.getEmail());
            checkPhoneNumber(signUpDto.getPhoneNumber());
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

            return "최초 통합회원 가입 성공하였습니다.";
        }
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
    public void checkLoginId(String loginId) {
        if (usersRepository.existsByLoginId(loginId)) {
            throw new GlobalException(ResponseStatus.DUPLICATE_ID);
        }
    }

    @Override
    public void checkEmail(String email) {
        if (usersRepository.existsByEmail(email)) {
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
        if (users.getIsIntegrated() == 0) throw new GlobalException(ResponseStatus.NO_REGISTER);

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
    public void checkPhoneNumber(String phoneNumber) {
        if (usersRepository.existsByPhoneNumber(phoneNumber)) {
            throw new GlobalException(ResponseStatus.DUPLICATED_USERS);
        }
    }
}
