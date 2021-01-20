package com.drogbalog.server.domain.user.api;

import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Api(tags = "User Auth Api")
@Log4j2
public class UserAuthApi {
    private final UserService userService;

    @GetMapping(value = "/test")
    public ResponseEntity<String> test(HttpSession session) {
        log.info(session.getAttribute("*"));
        log.info(session.getAttributeNames());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/signUp")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody UserRequest request) {
        UserResponse userResponse = userService.signUp(request);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody UserRequest request) {
        UserResponse userResponse = userService.login(request);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/accessToken")
    @ApiOperation(value = "access token 재발급 요청")
    public ResponseEntity<String> getAccessToken(@RequestParam String email ,
                                                       @RequestParam String refreshToken) {

        String newAccessToken = userService.getAccessToken(email, refreshToken);
        return new ResponseEntity<>(newAccessToken , HttpStatus.OK);
    }
}
