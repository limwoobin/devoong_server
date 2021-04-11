package com.drogbalog.server.domain.tags.api;

import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import com.drogbalog.server.domain.tags.service.TagsService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@Log4j2
@Api(tags = "Tags Api")
public class TagsApi {
    private final TagsService tagsService;

    @GetMapping("")
    public ResponseEntity<List<TagsResponse>> list() {
        List<TagsResponse> tagsResponseList = tagsService.getTagsList();
        return new ResponseEntity<>(tagsResponseList , OK);
    }
}
