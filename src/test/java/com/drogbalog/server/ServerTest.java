package com.drogbalog.server;

import com.drogbalog.server.global.retrofit.AbstractRetrofit;
import com.drogbalog.server.global.retrofit.impl.RetrofitSafe;
import org.junit.jupiter.api.Test;

public class ServerTest {

    @Test
    public void test() {
        AbstractRetrofit<String> retrofit = new RetrofitSafe<>();
    }

}
