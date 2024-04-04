package com.tyranno.ssg.auth.oauth.application;

import com.tyranno.ssg.auth.dto.*;
import com.tyranno.ssg.auth.oauth.dto.OAuthSignUpDto;
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
public class OAuthServiceImp implements OAuthService {
    private final UsersRepository usersRepository;
    private final MarketingRepository marketingRepository;
    private final MarketingInformationRepository marketingInformationRepository;
    private final DeliveryRepository deliveryRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public String signUpOAuth(OAuthSignUpDto oAuthSignUpDto) {
        // 이미 통합회원 일 시
        if(usersRepository.existsByNameAndEmail(oAuthSignUpDto.getName(),oAuthSignUpDto.getEmail())) {
            return connectOAuthAtUsers(oAuthSignUpDto);
        }
        return creatOAuthUsers(oAuthSignUpDto);
    }

    @Transactional
    protected String connectOAuthAtUsers(OAuthSignUpDto oAuthSignUpDto) {

    }

    @Transactional
    protected String creatOAuthUsers(OAuthSignUpDto oAuthSignUpDto) {
        //회원
        Users users = signUpDto.toUsersEntity();
        usersRepository.save(users);

        //마케팅
        for (MarketingType type : MarketingType.values()) {

            Byte isAgree = switch (type) {
                case SHINSEGAE -> signUpDto.getShinsegaeMarketingAgree();
                case SHINSEGAE_OPTION -> signUpDto.getShinsegaeOptionAgree();
                case SSG -> signUpDto.getSsgMarketingAgree();
            }; // default : 99 - 비동의

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
    public String loginUsers(LoginDto loginDto) {
        Users users = usersRepository.findByLoginId(loginDto.getLoginId())
                .orElseThrow(() -> new GlobalException(ResponseStatus.FAILED_TO_LOGIN));

        if (bCryptPasswordEncoder.matches(loginDto.getPassword(), users.getPassword())) {
            return jwtTokenProvider.generateToken(users);
        } else throw new GlobalException(ResponseStatus.FAILED_TO_LOGIN);
    }
}
