package com.tyranno.ssg.security;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final Environment env;

    public String tokenToUuid(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            return getUuid(jwt);
        }
        // 토큰이 유효하지 않은 경우, 에러 메시지 반환
        throw new GlobalException(ResponseStatus.TOKEN_NOT_VALID);
    }
    public String getUuid(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }
    // 주어진 토큰에서 UUID(사용자 식별자)를 추출

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    } //토큰에서 특정 클레임을 추출하는 범용 메소드

    public String generateToken(UserDetails userDetails) {
        return generateToken(Map.of(), userDetails);
    }

    public String generateToken( // 사용자 상세 정보(UserDetails)를 기반으로 JWT를 생성
                                 Map<String, Objects> extractClaims,
                                 UserDetails userDetails
    ) {

        log.info("generateToken {} ", userDetails);
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername()) // details의 username을 uuid로 설정해둠
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))// 'iat' 클레임으로 토큰 발행 시간 설정
                .setExpiration(new java.util.Date(System.currentTimeMillis() + env.getProperty("JWT.EXPIRATION_TIME", Long.class)))// 'exp' 클레임으로 토큰 만료 시간 설정
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // 서명 키와 알고리즘 설정
                .compact();
    }

    public String validateAndGetUserUuid(String jwt) { //토큰을 검증하고 유효한 경우 토큰에 담긴 사용자 UUID를 반환합니다. 토큰이 유효하지 않은 경우, 로그를 남기고 null을 반환
        try {
            return extractClaim(jwt, Claims::getSubject);
        } catch (NullPointerException e) {
            log.info("토큰에 담긴 유저 정보가 없습니다");
            return null;
        }
    }

    public boolean validateToken(String jwt, UserDetails userDetails) {
        final String uuid = getUuid(jwt);
        return (uuid.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }// 토큰이 주어진 사용자 상세 정보와 일치하는지 검증합니다. 사용자의 UUID가 일치하고 토큰이 만료되지 않았는지 확인

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new java.util.Date());
    }

    //토큰의 만료 여부를 검사
    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }//토큰에서 만료 시간을 추출

    private Claims extractAllClaims(String jwt) { // 토큰에서 모든 클레임을 추출
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(env.getProperty("JWT.SECRET_KEY"));
        return Keys.hmacShaKeyFor(keyBytes);
    } //환경 변수에서 가져온 비밀 키를 기반으로 JWT 서명에 사용할 Key 객체를 생성합니다. 비밀 키는 BASE64로 인코딩된 문자열로 환경 설정 파일에 저장
}