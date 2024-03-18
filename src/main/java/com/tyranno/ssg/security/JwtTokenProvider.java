package com.tyranno.ssg.security;

import com.tyranno.ssg.users.dto.TokenResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
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
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final Environment env;

    public String getUuid(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    //주어진 JWT 토큰에서 사용자 UUID(또는 사용자 이름 등)를 추출하여 반환

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }//주어진 JWT 토큰에서 클레임(claim)을 추출

    //    public String generateToken(UserDetails userDetails) {
//        return generateToken(Map.of(), userDetails);
//    }
//    //주어진 UserDetails 객체를 기반으로 JWT 토큰을 생성
//    public String generateToken( //주어진 추가 클레임과 UserDetails 객체를 기반으로 JWT 토큰을 생성
//            Map<String, Objects> extractClaims,
//            UserDetails userDetails
//    ) {
//
//        log.info("generateToken {} ", userDetails);
//        return Jwts.builder()
//                .setClaims(extractClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
//                .setExpiration(new java.util.Date(System.currentTimeMillis() + env.getProperty("JWT.EXPIRATION_TIME", Long.class)))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
    public TokenResponseDto generateTokens(UserDetails userDetails) {
        String accessToken = generateAccessToken(userDetails);
        String refreshToken = generateRefreshToken(userDetails);
        return new TokenResponseDto(accessToken, refreshToken);
    }

    public String generateAccessToken(UserDetails userDetails) {
        long expirationTimeLong = Long.parseLong(env.getProperty("JWT.ACCESS_TOKEN_EXPIRATION_TIME", "900000")); // 15 minutes default
        return generateJwt(Collections.emptyMap(), userDetails.getUsername(), expirationTimeLong);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        long expirationTimeLong = Long.parseLong(env.getProperty("JWT.REFRESH_TOKEN_EXPIRATION_TIME", "604800000")); // 7 days default
        return generateJwt(Collections.emptyMap(), userDetails.getUsername(), expirationTimeLong);
    }

    private String generateJwt(Map<String, Object> claims, String subject, long expirationTime) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateAndGetUserUuid(String token) { //주어진 JWT 토큰을 유효성 검사하고 사용자 UUID를 반환
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (NullPointerException e) {
            log.info("토큰에 담긴 유저 정보가 없습니다");
            return null;
        }
    }

    //    public boolean validateToken(String token, UserDetails userDetails) { //주어진 JWT 토큰을 유효성 검사하고, 해당 토큰이 주어진 사용자에 대해 유효한지 확인
//        final String uuid = getUuid(token);
//        return (uuid.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
    public boolean validateToken(String token) { // 토큰 변조, 유호시간 내에 있는지 확인
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new java.util.Date());
    } //주어진 JWT 토큰이 만료되었는지 확인

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 주어진 JWT 토큰의 만료 시간을 추출
    private Claims extractAllClaims(String token) {//주어진 JWT 토큰에서 모든 클레임을 추출
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //    private Key getSigningKey() { //서명 키를 가져오는 메서드. 주어진 시크릿 키를 바탕으로 서명 키를 생성하여 반환
//        byte[] keyBytes = Decoders.BASE64.decode(env.getProperty("JWT.SECRET_KEY"));
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    private Key getSigningKey() {
        String secretKey = env.getProperty("JWT.SECRET_KEY", "mySecretKey");
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
