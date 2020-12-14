package com.drogbalog.server;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

public class ServerTest {

    @Test
    public void test() {
        System.out.println(!StringUtils.isEmpty(null));
        System.out.println(!StringUtils.isEmpty("zz"));
    }

}
