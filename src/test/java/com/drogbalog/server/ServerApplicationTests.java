package com.drogbalog.server;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
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
class ServerApplicationTests {

}
