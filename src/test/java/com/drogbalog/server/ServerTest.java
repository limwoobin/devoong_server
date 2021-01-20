package com.drogbalog.server;

import com.drogbalog.server.domain.user.repository.UserRepository;
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
        long a = 1611122008666L;
        long b = 1000L;
        System.out.println(a/b);
    }
}
