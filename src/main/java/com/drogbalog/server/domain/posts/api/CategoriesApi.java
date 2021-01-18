package com.drogbalog.server.domain.posts.api;

import com.drogbalog.server.domain.posts.domain.dto.CategoriesResponse;
import com.drogbalog.server.domain.posts.service.CategoriesService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Log4j2
public class CategoriesApi {
    private final CategoriesService categoriesService;

    @GetMapping(value = "")
    @ApiOperation(value = "카테고리 목록 조회")
    public ResponseEntity<List<CategoriesResponse>> getCategories() {
        List<CategoriesResponse> categoriesResponses = categoriesService.getCategories();
        return new ResponseEntity<>(categoriesResponses , HttpStatus.OK);
    }
}
