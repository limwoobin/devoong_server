package com.drogbalog.server.global.config.security.jwt;

import com.drogbalog.server.global.config.security.auth.Role;
import com.drogbalog.server.user.domain.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import static com.drogbalog.server.global.util.StaticInfo.DR_HEADER_TOKEN;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret_key}")
    private String secretKey;

    @Value("${jwt.token_valid_time}")
    private long tokenValidTime;

    @Value("${jwt.refreshToken_valid_time}")
    private long refreshTokenValidTime;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    private String doGenerateToken(String userPrimaryKey , long validTime) {
        Claims claims = Jwts.claims().setSubject(userPrimaryKey);
        claims.put("role" , Role.USER);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(createExpiredTime(validTime))
                .signWith(SignatureAlgorithm.HS256 , secretKey)
                .compact();
    }

    private String generateAccessToken(String userPrimaryKey) {
        return doGenerateToken(userPrimaryKey , tokenValidTime);
    }

    private String generateRefreshToken(String userPrimaryKey) {
        return doGenerateToken(userPrimaryKey , refreshTokenValidTime);
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

    String getUserPrimaryKey(String token) {
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
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
        return !claimsJws.getBody().getExpiration().before(new Date());
    }

    public UserDto generateTokens(UserDto userDto) {
        userDto.setAccessToken(this.generateAccessToken(userDto.getEmail()));
        userDto.setRefreshToken(this.generateRefreshToken(userDto.getEmail()));

        return userDto;
    }
}
