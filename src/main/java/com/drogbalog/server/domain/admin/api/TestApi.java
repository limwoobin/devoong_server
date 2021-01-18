package com.drogbalog.server.domain.admin.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {

    @PostMapping(value = "/test")
    public String test() {
        return "ok";
    }
}
