package com.drogbalog.server.user.controller;

import com.drogbalog.server.user.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
//    private final Service service;
    private final BeanFactory factory;

    public UserController(BeanFactory factory) {
        this.factory = factory;
    }


    @GetMapping("/test")
    public String test() {
        Service service = (Service) factory.getBean("user");
        Service service2 = (Service) factory.getBean("test");

        System.out.println(service.serviceTest());
        System.out.println(service2.serviceTest());

        return "zz";
    }
}
