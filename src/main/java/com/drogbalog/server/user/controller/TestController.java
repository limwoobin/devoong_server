package com.drogbalog.server.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.drogbalog.server.global.util.StaticInfo.DR_HEADER_TOKEN;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    public ResponseEntity<String> test(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token) {

        System.out.println(token);
        return new ResponseEntity<>("test" , HttpStatus.OK);
    }
}
