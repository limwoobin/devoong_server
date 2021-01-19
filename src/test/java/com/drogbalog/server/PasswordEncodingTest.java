package com.drogbalog.server;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StopWatch;

@Log4j2
@SpringBootTest
public class PasswordEncodingTest {

    @Autowired
    BeanFactory beanFactory;

    @Test
    public void encoderTest() {
        PasswordEncoder passwordEncoder = (PasswordEncoder) beanFactory.getBean("passwordEncoder");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String password = "passwordTest22";
        String password2 = "passwordTest22";

        System.out.println(passwordEncoder.encode(password));
        System.out.println(passwordEncoder.encode(password));
        System.out.println("----------------------------------");
        System.out.println(passwordEncoder.encode(password2));
        System.out.println(passwordEncoder.encode(password2));

        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }
}
