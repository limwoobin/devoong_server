package com.drogbalog.server.user.controller;

import com.drogbalog.server.user.domain.dto.UserDto;
import com.drogbalog.server.user.domain.dto.UserHistoryDto;
import com.drogbalog.server.user.service.UserHistoryService;
import com.drogbalog.server.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "회원가입")
    public ResponseEntity<UserDto> signUp() {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<UserDto> login() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "회원정보 조회")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable(name = "userId") long userId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/history/{userId}")
    @ApiOperation(value = "로그인 내역 조회")
    public ResponseEntity<UserHistoryDto> getUserLoginHistory(@PathVariable(name = "userId") long userId) {
        UserHistoryDto userHistoryDto = userHistoryService.getUserLoginHistory(userId);

        return new ResponseEntity<>(userHistoryDto , HttpStatus.OK);
    }
}
