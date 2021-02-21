package com.drogbalog.server;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Log4j2
@SpringBootTest(
        properties = {
                    "value=test",
                    "propertyValue=property"
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(value = "test")
class ServerApplicationTests {

    @Value("value")
    private String value1;

    @Value("propertyValue")
    private String value2;

    @Test
    public void test() {
        log.info(value1);
        log.info(value2);
    }
}
