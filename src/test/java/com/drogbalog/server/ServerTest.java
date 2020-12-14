package com.drogbalog.server;

import com.drogbalog.server.global.config.web.MethodType;
import com.drogbalog.server.global.exception.DrogbalogException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class ServerTest {

    @Test
    public void test() {
        System.out.println(Arrays.toString(MethodType.values()));
        System.out.println(Arrays.asList(MethodType.values()));
    }

}
