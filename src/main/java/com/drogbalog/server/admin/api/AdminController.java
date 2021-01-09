package com.drogbalog.server.admin.api;

import com.drogbalog.server.posts.domain.dto.PostsDto;
import com.drogbalog.server.posts.domain.request.PostsRequest;
import com.drogbalog.server.posts.service.PostsService;
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
    public ResponseEntity<PostsDto> createPosts(@RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token ,
                                                @RequestBody PostsRequest request) {
        PostsDto postsDto = postsService.createPosts(request);
        return new ResponseEntity<>(postsDto , HttpStatus.CREATED);
    }

    @PutMapping(value = "/posts/{postsId}")
    @ApiOperation(value = "게시글 수정")
    public ResponseEntity<PostsDto> updatePosts(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token,
            @PathVariable(name = "postsId") long postsId , @RequestBody PostsRequest request) {
        request.setId(postsId);
        PostsDto postsDto = postsService.updatePosts(request);
        return new ResponseEntity<>(postsDto , HttpStatus.OK);
    }
}
