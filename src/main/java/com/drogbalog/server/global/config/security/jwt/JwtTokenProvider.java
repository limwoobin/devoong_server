package com.drogbalog.server.global.config.security.jwt;

import com.drogbalog.server.domain.user.domain.response.JwtResponse;
import com.drogbalog.server.global.config.security.Role;
import com.drogbalog.server.global.exception.auth.AuthExceptionCode;
import com.drogbalog.server.global.exception.UnAuthorizedException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.drogbalog.server.global.utils.Constants.DR_HEADER_TOKEN;

@RequiredArgsConstructor
@Component
@Log4j2
public class JwtTokenProvider {
    private final UserDetailsService userDetailsService;
    private final RedisTemplate<String , Object> redisTemplate;

    @Value("${jwt.secret_key}")
    private String secretKey;

    @Value("${jwt.access_token.validate_time}")
    private long accessTokenExpiredTime;

    @Value("${jwt.refresh_token.validate_time}")
    private long refreshTokenExpiredTime;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    private String doGenerateToken(String userPrimaryKey , long expiredTime) {
        Claims claims = Jwts.claims().setSubject(userPrimaryKey);
        claims.put("role" , Role.USER);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(createExpiredTime(expiredTime))
                .signWith(SignatureAlgorithm.HS256 , secretKey)
                .compact();
    }

    public String generateAccessToken(String userPrimaryKey) {
        log.info("accessTokenExpiredTime: {}" , accessTokenExpiredTime);
        return doGenerateToken(userPrimaryKey , accessTokenExpiredTime);
    }

    private String generateRefreshToken(String userPrimaryKey) {
        log.info("refreshTokenExpiredTime: {}" , refreshTokenExpiredTime);
        return doGenerateToken(userPrimaryKey , refreshTokenExpiredTime);
    }

    private static Date createExpiredTime(long tokenValidTime) {
        Date now = new Date();
        return new Date(now.getTime() + tokenValidTime);
    }

    Authentication getAuthentication(String token) {
        String userPrimaryKey = this.getUserPrimaryKey(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userPrimaryKey);
        return new UsernamePasswordAuthenticationToken(userDetails , "" , userDetails.getAuthorities());
    }

    public String getUserPrimaryKey(String token) {
        return getClaimFromToken(token , Claims::getSubject);
    }

    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    String resolveToken(HttpServletRequest request) {
        return request.getHeader(DR_HEADER_TOKEN);
    }

    boolean validateToken(String jwtToken) {
        Jws<Claims> claimsJws = null;
        try {
            claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
        } catch (MalformedJwtException e) {
            log.info("error message: {}" , e.getMessage());
            throw new UnAuthorizedException(AuthExceptionCode.MALFORMED.getCode() , e.getMessage());
        } catch (SignatureException e) {
            log.info("error message: {}" , e.getMessage());
            throw new UnAuthorizedException(AuthExceptionCode.SIGNATURE.getCode() , e.getMessage());
        } catch (ExpiredJwtException e) {
            log.info("error message: {}" , e.getMessage());
            throw new UnAuthorizedException(AuthExceptionCode.EXPIRED.getCode() , e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.info("error message: {}" , e.getMessage());
            throw new UnAuthorizedException(AuthExceptionCode.UNSUPPORTED.getCode() , e.getMessage());
        }

        return !claimsJws.getBody().getExpiration().before(new Date());
    }

    public Date getExpirationDate(String accessToken) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken);
        return claimsJws.getBody().getExpiration();
    }

    public JwtResponse generateTokens(String email) {
        String accessToken = this.generateAccessToken(email);
        String refreshToken = this.generateRefreshToken(email);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setAccessToken(accessToken);
        jwtResponse.setRefreshToken(refreshToken);
        saveRefreshToken(email , refreshToken);
        return jwtResponse;
    }

    private void saveRefreshToken(String email , String refreshToken) {
        redisTemplate.opsForValue().set(email , refreshToken);
        redisTemplate.expire(email , 30 , TimeUnit.DAYS);
    }

    public boolean refreshTokenVerification(String email , String refreshToken) {
        String targetRefreshToken = (String) redisTemplate.opsForValue().get(email);
        return refreshToken.equals(targetRefreshToken) && validateToken(targetRefreshToken);
    }
}
