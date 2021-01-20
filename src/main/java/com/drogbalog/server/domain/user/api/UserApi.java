package com.drogbalog.server.domain.user.api;

import com.drogbalog.server.global.config.security.jwt.JwtTokenProvider;
import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.service.UserService;
import com.drogbalog.server.global.exception.UnAuthorizedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.drogbalog.server.global.util.StaticInfo.DR_HEADER_TOKEN;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Log4j2
@Api(tags = "User Api")
public class UserApi {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "회원정보 조회")
    public ResponseEntity<UserResponse> getUserInfo(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token) {

        String email = jwtTokenProvider.getUserPrimaryKey(token);
        UserResponse userResponse = userService.getUserInfo(email);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping(value = "")
    @ApiOperation(value = "회원정보 수정")
    public ResponseEntity<UserResponse> updateUser(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token ,
            @RequestBody UserRequest request) {
        UserResponse userResponse = userService.updateUserInfo(request);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    @ApiOperation(value = "회원 탈퇴")
    public ResponseEntity<Void> deleteUser(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token ,
            @PathVariable(name = "userId") long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
