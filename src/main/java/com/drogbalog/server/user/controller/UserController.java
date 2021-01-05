package com.drogbalog.server.user.controller;

import com.drogbalog.server.global.config.security.auth.Role;
import com.drogbalog.server.global.config.security.jwt.JwtTokenProvider;
import com.drogbalog.server.user.domain.dto.UserDto;
import com.drogbalog.server.user.domain.request.UserRequest;
import com.drogbalog.server.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

import static com.drogbalog.server.global.util.StaticInfo.DR_HEADER_TOKEN;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(tags = "User Api")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @PostMapping(value = "/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<UserDto> login(@RequestBody UserRequest request) {
        UserDto userDto = new UserDto();
        userDto.setJwtToken(jwtTokenProvider.generateToken(request.getEmail()));

        return new ResponseEntity<>(userDto , HttpStatus.OK);
    }

    @GetMapping(value = "/logout")
    @ApiOperation(value = "로그아웃")
    public ResponseEntity<HttpStatus> logout(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token) {
        logger.info(token);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "회원정보 조회")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable(name = "userId") long userId) {
        UserDto userDto = userService.getUserInfo(userId);
        return new ResponseEntity<>(userDto , HttpStatus.OK);
    }

    @PostMapping(value = "")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<UserDto> signUp(UserRequest request) {
        UserDto userDto = userService.signUp(request);
        return new ResponseEntity<>(userDto , HttpStatus.CREATED);
    }

    @PutMapping(value = "")
    @ApiOperation(value = "회원정보 수정")
    public ResponseEntity<UserDto> updateUser(UserRequest request) {
        UserDto userDto = userService.updateUserInfo(request);
        return new ResponseEntity<>(userDto , HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    @ApiOperation(value = "회원 탈퇴")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
