package com.drogbalog.server.domain.visit.api;

import com.drogbalog.server.domain.visit.domain.VisitResponse;
import com.drogbalog.server.domain.visit.service.VisitService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Log4j2
@Api(tags = "Visit Api")
public class VisitorApi {
    private final VisitService visitService;

    @GetMapping("visit")
    public ResponseEntity<VisitResponse> visit() {
        VisitResponse visitResponse = visitService.updateVisit();
        return new ResponseEntity<>(visitResponse , HttpStatus.OK);
    }
}
