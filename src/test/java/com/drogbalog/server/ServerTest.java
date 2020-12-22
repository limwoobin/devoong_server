package com.drogbalog.server;

import com.drogbalog.server.global.retrofit.RetrofitFactory;
import com.drogbalog.server.global.retrofit.impl.RetrofitSafe;
import org.junit.jupiter.api.Test;

public class ServerTest {

    @Test
    public void test() {
        RetrofitFactory<String> retrofit = new RetrofitSafe<>();
    }

}
