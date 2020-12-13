package com.drogbalog.server;

import com.drogbalog.server.global.exception.DrogbalogException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class ServerTest {

    @Test
    public void test() {
        Exception e = new Exception();
        System.out.println(e.getMessage());
        System.out.println(e.getCause());
    }

}
