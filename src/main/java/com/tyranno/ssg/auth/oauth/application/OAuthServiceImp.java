package com.tyranno.ssg.auth.oauth.application;

import com.tyranno.ssg.auth.application.AuthService;
import com.tyranno.ssg.auth.dto.MarketingAgreeDto;
import com.tyranno.ssg.auth.oauth.domain.OAuth;
import com.tyranno.ssg.auth.oauth.dto.OAuthExternalIdDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthSignUpDto;
import com.tyranno.ssg.auth.oauth.infrastructure.OAuthRepository;
import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.delivery.infrastructure.DeliveryRepository;
import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.security.JwtTokenProvider;
import com.tyranno.ssg.users.domain.Users;
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
    private final DeliveryRepository deliveryRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final OAuthRepository oAuthRepository;
    private final AuthService authService;

    @Transactional
    @Override
    public String signUpOAuth(OAuthSignUpDto oAuthSignUpDto) {
        String result = "";
        //회원
        Users users = usersRepository.findByNameAndEmail(oAuthSignUpDto.getName(), oAuthSignUpDto.getEmail());

        // 통합회원 가입 이력이 없을 시
        if (users == null) {
            //회원
            users = oAuthSignUpDto.toUsersEntity();
            usersRepository.save(users);

            //마케팅
            MarketingAgreeDto marketingAgreeDto = new MarketingAgreeDto(
                    oAuthSignUpDto.getShinsegaeMarketingAgree(),
                    oAuthSignUpDto.getShinsegaeOptionAgree(),
                    oAuthSignUpDto.getSsgMarketingAgree());

            authService.addMarketingInformation(marketingAgreeDto, users);

            // 배송지
            Delivery delivery = oAuthSignUpDto.toDeliveryEntity(users);
            deliveryRepository.save(delivery);

            result = "소셜 회원가입에 성공하였습니다.";
        } else result = "통합 회원 정보와 연결하였습니다.";

        oAuthRepository.save(oAuthSignUpDto.toOAuthEntity(users));

        return result;

    }

    @Override
    public String loginOAuth(OAuthExternalIdDto oAuthExternalIdDto) {
        OAuth oAuth = oAuthRepository.findByExternalId(oAuthExternalIdDto.getOAuthExternalId())
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_OAUTH));

        return jwtTokenProvider.generateToken(oAuth.getUsers());
    }
}
