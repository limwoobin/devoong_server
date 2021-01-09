package com.drogbalog.server.posts.controller;

import com.drogbalog.server.global.code.PostsType;
import com.drogbalog.server.global.config.security.jwt.JwtTokenProvider;
import com.drogbalog.server.posts.domain.dto.PostsDto;
import com.drogbalog.server.posts.domain.request.PostsRequest;
import com.drogbalog.server.posts.service.PostsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.drogbalog.server.global.util.StaticInfo.DR_HEADER_TOKEN;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {
    private final PostsService postsService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping(value = "")
    @ApiOperation(value = "게시글 목록 조회")
    public ResponseEntity<Page<PostsDto>> getPostsList(
            @PageableDefault(size = 10 , sort = "createdDate" , direction = Sort.Direction.DESC) final Pageable pageable) {
        Page<PostsDto> postsList = postsService.getPostsList(pageable);
        return new ResponseEntity<>(postsList , HttpStatus.OK);
    }

    @GetMapping(value = "/{postsType}")
    @ApiOperation(value = "카테고리 별 게시글 목록 조회")
    public ResponseEntity<Page<PostsDto>> getPostsListByPostsType(
            @PageableDefault(size = 10 , sort = "createdDate" , direction = Sort.Direction.DESC) final Pageable pageable ,
            @PathVariable(name = "postsType") PostsType postsType) {
        Page<PostsDto> postsList = postsService.getPostsListByPostsType(postsType);
        return new ResponseEntity<>(postsList , HttpStatus.OK);
    }

    @GetMapping(value = "/{postsId}")
    @ApiOperation(value = "게시글 조회")
    public ResponseEntity<PostsDto> getPostsDetail(@PathVariable(name = "postsId") long postsId) {
        PostsDto postsDto = postsService.getPosts(postsId);
        return new ResponseEntity<>(postsDto , HttpStatus.OK);
    }
}
