package com.drogbalog.server.user.controller;

import com.drogbalog.server.global.exception.DrogbalogException;
import com.drogbalog.server.user.service.UserHistoryService;
import com.drogbalog.server.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = "User Api")
public class UserController {
    private final UserService userService;
    private final UserHistoryService userHistoryService;

    @GetMapping(value = "")
    public ResponseEntity<HttpStatus> testResponse() {
        String a = null;
        if (a == null) {
            throw new DrogbalogException("ang~~");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<HttpStatus> testResponse2() {
        userService.test();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
