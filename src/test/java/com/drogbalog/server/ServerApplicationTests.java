package com.drogbalog.server;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@Log4j2
@ActiveProfiles(value = "test")
class ServerApplicationTests {

    @Test
    public void test() {
        log.info("zz");
    }
}
