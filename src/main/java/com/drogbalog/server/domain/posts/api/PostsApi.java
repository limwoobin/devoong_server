package com.drogbalog.server.domain.posts.api;

import com.drogbalog.server.domain.posts.domain.dto.PostsResponse;
import com.drogbalog.server.domain.posts.service.PostsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Log4j2
public class PostsApi {
    private final PostsService postsService;

    @GetMapping(value = "")
    @ApiOperation(value = "게시글 목록 조회")
    public ResponseEntity<Page<PostsResponse>> getPostsList(
            @PageableDefault(size = 10 , sort = "createdDate" , direction = Sort.Direction.DESC) final Pageable pageable) {
        Page<PostsResponse> postsList = postsService.getPostsList(pageable);
        return new ResponseEntity<>(postsList , HttpStatus.OK);
    }

    @GetMapping(value = "/{categoryId}")
    @ApiOperation(value = "카테고리 별 게시글 목록 조회")
    public ResponseEntity<Page<PostsResponse>> getPostsListByCategoryId(
            @PageableDefault(size = 10 , sort = "createdDate" , direction = Sort.Direction.DESC) final Pageable pageable ,
            @PathVariable(name = "categoryId") long categoryId) {
        Page<PostsResponse> postsList = postsService.getPostsListByCategoryId(categoryId);
        return new ResponseEntity<>(postsList , HttpStatus.OK);
    }

    @GetMapping(value = "/{postsId}")
    @ApiOperation(value = "게시글 조회")
    public ResponseEntity<PostsResponse> getPostsDetail(@PathVariable(name = "postsId") long postsId) {
        PostsResponse PostsResponse = postsService.getPosts(postsId);
        return new ResponseEntity<>(PostsResponse , HttpStatus.OK);
    }
}
