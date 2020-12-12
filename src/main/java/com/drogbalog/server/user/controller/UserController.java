package com.drogbalog.server.user.controller;

import com.drogbalog.server.user.domain.dto.UserDto;
import com.drogbalog.server.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = "User Api")
public class UserController {
    private final UserService userService;

}
