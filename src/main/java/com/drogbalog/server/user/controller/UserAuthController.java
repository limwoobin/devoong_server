package com.drogbalog.server.user.controller;

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

import javax.validation.Valid;

import static com.drogbalog.server.global.util.StaticInfo.DR_HEADER_TOKEN;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Api(tags = "User Auth Api")
public class UserAuthController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @PostMapping(value = "/signUp")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody UserRequest request) {
        UserDto userDto = userService.signUp(request);

        return new ResponseEntity<>(userDto , HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<UserDto> login(@Valid @RequestBody UserRequest request) {
        UserDto userDto = userService.login(request);
        userDto = jwtTokenProvider.generateTokens(userDto);

        return new ResponseEntity<>(userDto , HttpStatus.OK);
    }

    @GetMapping(value = "/logout")
    @ApiOperation(value = "로그아웃")
    public ResponseEntity<HttpStatus> logout(@RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token) {
        logger.info(token);
        // todo: redis 연동

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
