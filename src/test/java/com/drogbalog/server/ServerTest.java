package com.drogbalog.server;

import com.drogbalog.server.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ServerTest {
    @Autowired
    private UserRepository repository;

    private BigInteger bigInteger = BigInteger.ZERO;


    @Test
    public void test() {
        this.bigInteger = bigInteger.add(BigInteger.ONE);
        this.bigInteger = bigInteger.add(BigInteger.ONE);
        this.bigInteger = bigInteger.add(BigInteger.ONE);
        this.bigInteger = bigInteger.add(BigInteger.ONE);
        this.bigInteger = bigInteger.add(BigInteger.ONE);

        System.out.println(bigInteger);

        int a = Integer.parseInt(bigInteger.toString());
        assertEquals(5 , a);
    }
}
