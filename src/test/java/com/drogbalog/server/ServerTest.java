package com.drogbalog.server;

import com.drogbalog.server.domain.user.repository.UserRepository;
import com.drogbalog.server.global.config.security.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigInteger;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
public class ServerTest {

    @Test
    public void test() {
        for (int i=0; i<10; i++) {
            System.out.println(doGenerateToken("drogba02@naver.com" , 18400));
        }
    }

    private String doGenerateToken(String userPrimaryKey , long expiredTime) {
        Claims claims = Jwts.claims().setSubject(userPrimaryKey);
        claims.put("role" , Role.USER);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(createExpiredTime(expiredTime))
                .signWith(SignatureAlgorithm.HS256 , "dlog_secret_key")
                .compact();
    }

    private static Date createExpiredTime(long tokenValidTime) {
        Date now = new Date();
        return new Date(now.getTime() + tokenValidTime);
    }
}
