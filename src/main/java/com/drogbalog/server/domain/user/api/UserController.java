package com.drogbalog.server.domain.user.api;

import com.drogbalog.server.global.config.security.jwt.JwtTokenProvider;
import com.drogbalog.server.domain.user.domain.dto.UserDto;
import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.drogbalog.server.global.util.StaticInfo.DR_HEADER_TOKEN;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(tags = "User Api")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "회원정보 조회")
    public ResponseEntity<UserDto> getUserInfo(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token ,
            @PathVariable(name = "userId") long userId) {
        UserDto userDto = userService.getUserInfo(userId);
        return new ResponseEntity<>(userDto , HttpStatus.OK);
    }

    @PutMapping(value = "")
    @ApiOperation(value = "회원정보 수정")
    public ResponseEntity<UserDto> updateUser(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token ,
            @RequestBody UserRequest request) {
        UserDto userDto = userService.updateUserInfo(request);
        return new ResponseEntity<>(userDto , HttpStatus.OK);
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
