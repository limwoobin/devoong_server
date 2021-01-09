package com.drogbalog.server.domain.user.api;

import com.drogbalog.server.global.exception.DrogbalogException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping(value = "/test")
    public ResponseEntity<DrogbalogException> test() {
        DrogbalogException exception = new DrogbalogException();
        exception.setCode(200);
        exception.setStatus(HttpStatus.OK);
        exception.setMessage("zzz");
        return new ResponseEntity<>(exception , HttpStatus.OK);
    }
}
