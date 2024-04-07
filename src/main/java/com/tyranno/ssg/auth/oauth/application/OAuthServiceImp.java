package com.tyranno.ssg.auth.oauth.application;

import com.tyranno.ssg.auth.application.AuthService;
import com.tyranno.ssg.auth.dto.MarketingAgreeDto;
import com.tyranno.ssg.auth.oauth.domain.OAuth;
import com.tyranno.ssg.auth.oauth.dto.OAuthExternalIdDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthInfoDto;
import com.tyranno.ssg.auth.oauth.dto.OAuthSignUpDto;
import com.tyranno.ssg.auth.oauth.infrastructure.OAuthRepository;
import com.tyranno.ssg.delivery.domain.Delivery;
import com.tyranno.ssg.delivery.infrastructure.DeliveryRepository;
import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.security.JwtTokenProvider;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthServiceImp implements OAuthService {
    private final UsersRepository usersRepository;
    private final DeliveryRepository deliveryRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final OAuthRepository oauthRepository;
    private final AuthService authService;


    @Override
    // 기존 회원 여부 조회 (소셜 아이디)
    public String checkOAuthUsersByOAuthId(OAuthInfoDto oauthInfoDto) {

        if (oauthRepository.existsByExternalId(oauthInfoDto.getOauthExternalId())) {
            return "소셜 회원입니다.";
        }
        else if(usersRepository.existsByNameAndEmail(oauthInfoDto.getName(), oauthInfoDto.getEmail())) {
            return "통합 회원입니다.";
        }
        else return "회원가입 이력이 없습니다.";
    }

    @Override
    @Transactional
    public void connectOAuth(OAuthInfoDto oauthInfoDto){
        Users users = usersRepository.findByNameAndEmail(oauthInfoDto.getName(), oauthInfoDto.getEmail())
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
        oauthRepository.save(oauthInfoDto.toEntity(users));
    }

    @Transactional
    @Override
    public void signUpOAuth(OAuthSignUpDto oauthSignUpDto) {

        Users users = oauthSignUpDto.toUsersEntity();
        usersRepository.save(users);

        MarketingAgreeDto marketingAgreeDto = new MarketingAgreeDto(
                oauthSignUpDto.getShinsegaeMarketingAgree(),
                oauthSignUpDto.getShinsegaeOptionAgree(),
                oauthSignUpDto.getSsgMarketingAgree());

        authService.addMarketingInformation(marketingAgreeDto, users);

        Delivery delivery = oauthSignUpDto.toDeliveryEntity(users);
        deliveryRepository.save(delivery);

        //oauthRepository.save(oauthSignUpDto.toOAuthEntity(users));

        OAuth oauth = oauthSignUpDto.toOAuthEntity(users);
        System.out.println("OAuth External ID before save: " + oauth.getExternalId()); // 로깅 추가
        oauthRepository.save(oauth);

}

@Override
public String loginOAuth(OAuthExternalIdDto oauthExternalIdDto) {
    OAuth oauth = oauthRepository.findByExternalId(oauthExternalIdDto.getOauthExternalId())
            .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_OAUTH));

    return jwtTokenProvider.generateToken(oauth.getUsers());
}
}
