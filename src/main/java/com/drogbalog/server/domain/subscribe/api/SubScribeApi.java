package com.drogbalog.server.domain.subscribe.api;

import com.drogbalog.server.domain.subscribe.domain.response.SubScribeResponse;
import com.drogbalog.server.domain.subscribe.service.SubScribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscribe")
@RequiredArgsConstructor
@Log4j2
public class SubScribeApi {
    private final SubScribeService subScribeService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<SubScribeResponse>> list() {
        List<SubScribeResponse> subscribeList = subScribeService.getSubscribeList();
        return new ResponseEntity<>(subscribeList , HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<SubScribeResponse> subscribe(@RequestParam String email) {
        return new ResponseEntity<>(subScribeService.subscribe(email) , HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> unSubscribe(@PathVariable String email) {
        subScribeService.unSubscribe(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
