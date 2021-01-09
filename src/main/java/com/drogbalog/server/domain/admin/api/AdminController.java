package com.drogbalog.server.domain.admin.api;

import com.drogbalog.server.domain.posts.domain.dto.PostsResponse;
import com.drogbalog.server.domain.posts.domain.request.PostsRequest;
import com.drogbalog.server.domain.posts.service.PostsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.drogbalog.server.global.util.StaticInfo.DR_HEADER_TOKEN;

@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {
    private final PostsService postsService;

    @PostMapping(value = "/posts")
    @ApiOperation(value = "게시글 작성")
    public ResponseEntity<PostsResponse> createPosts(@RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token ,
                                                     @RequestBody PostsRequest request) {
        PostsResponse postsDto = postsService.createPosts(request);
        return new ResponseEntity<>(postsDto , HttpStatus.CREATED);
    }

    @PutMapping(value = "/posts/{postsId}")
    @ApiOperation(value = "게시글 수정")
    public ResponseEntity<PostsResponse> updatePosts(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token,
            @PathVariable(name = "postsId") long postsId , @RequestBody PostsRequest request) {
        request.setId(postsId);
        PostsResponse postsDto = postsService.updatePosts(request);
        return new ResponseEntity<>(postsDto , HttpStatus.OK);
    }
}
