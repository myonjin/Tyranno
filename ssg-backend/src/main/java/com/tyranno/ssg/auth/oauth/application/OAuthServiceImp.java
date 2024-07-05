package com.tyranno.ssg.auth.oauth.application;

import com.tyranno.ssg.auth.application.AuthService;
import com.tyranno.ssg.auth.dto.MarketingAgreeDto;
import com.tyranno.ssg.auth.oauth.domain.OAuth;
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
    @Transactional
    public String loginOAuth(OAuthInfoDto oauthInfoDto) {
        OAuth oauth = oauthRepository.findByExternalId(oauthInfoDto.getOauthExternalId());
        // 이미 소셜회원인 경우
        if (oauth != null) return jwtTokenProvider.generateToken(oauth.getUsers());

        else {
            Users users = usersRepository.findByNameAndEmail(oauthInfoDto.getName(), oauthInfoDto.getEmail())
                    .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS)); // 비회원인 경우
            // 통합회원인 경우
            oauth = oauthInfoDto.toEntity(users);
            oauthRepository.save(oauth);
            return jwtTokenProvider.generateToken(oauth.getUsers());
        }
    }

    @Transactional
    @Override
    public String signUpOAuth(OAuthSignUpDto oauthSignUpDto) {
        // email, phoneNumber 중복 방지
        authService.checkEmail(oauthSignUpDto.getEmail());
        authService.checkPhoneNumber(oauthSignUpDto.getPhoneNumber());

        Users users = oauthSignUpDto.toUsersEntity();
        usersRepository.save(users);

        MarketingAgreeDto marketingAgreeDto = new MarketingAgreeDto(
                oauthSignUpDto.getShinsegaeMarketingAgree(),
                oauthSignUpDto.getShinsegaeOptionAgree(),
                oauthSignUpDto.getSsgMarketingAgree());

        authService.addMarketingInformation(marketingAgreeDto, users);

        Delivery delivery = oauthSignUpDto.toDeliveryEntity(users);
        deliveryRepository.save(delivery);

        OAuth oauth = oauthSignUpDto.toOAuthEntity(users);
        oauthRepository.save(oauth);

        return jwtTokenProvider.generateToken(oauth.getUsers());
    }
    //    @Override
//    @Transactional
//    public void connectOAuth(OAuthInfoDto oauthInfoDto){
//        Users users = usersRepository.findByNameAndEmail(oauthInfoDto.getName(), oauthInfoDto.getEmail())
//                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
//        oauthRepository.save(oauthInfoDto.toEntity(users));
//    }
//    private String loginOAuth(OAuth oauth) {
//        return jwtTokenProvider.generateToken(oauth.getUsers());
//    }
}
