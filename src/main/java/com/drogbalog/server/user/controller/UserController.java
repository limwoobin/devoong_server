package com.drogbalog.server.user.controller;

import com.drogbalog.server.user.domain.dto.UserDto;
import com.drogbalog.server.user.domain.dto.UserHistoryDto;
import com.drogbalog.server.user.service.UserHistoryService;
import com.drogbalog.server.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = "User Api")
public class UserController {
    private final UserService userService;
    private final UserHistoryService userHistoryService;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @PostMapping(value = "/signUp")
    public ResponseEntity<UserDto> signUp() {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDto> login() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable(name = "userId") long userId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/history/{userId}")
    public ResponseEntity<UserHistoryDto> getUserLoginHistory(@PathVariable(name = "userId") long userId) {
        UserHistoryDto userHistoryDto = userHistoryService.getUserLoginHistory(userId);

        return new ResponseEntity<>(userHistoryDto , HttpStatus.OK);
    }
}
