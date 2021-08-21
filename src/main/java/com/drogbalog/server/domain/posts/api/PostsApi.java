package com.drogbalog.server.domain.posts.api;

import com.drogbalog.server.domain.posts.domain.dto.Archive;
import com.drogbalog.server.domain.posts.domain.dto.ArchiveByYear;
import com.drogbalog.server.domain.posts.domain.response.PostsResponse;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Log4j2
public class PostsApi {
    private final PostsService postsService;

    @GetMapping(value = "")
    @ApiOperation(value = "게시글 목록 조회")
    public ResponseEntity<Page<PostsResponse>> getPostsList(
            @PageableDefault(size = 5 , sort = "id" , direction = Sort.Direction.DESC) final Pageable pageable) {
        Page<PostsResponse> postsList = postsService.getPostsList(pageable);
        return new ResponseEntity<>(postsList , HttpStatus.OK);
    }

    @GetMapping(value = "/{postsId}")
    @ApiOperation(value = "게시글 조회")
    public ResponseEntity<PostsResponse> getPostsDetail(@PathVariable(name = "postsId") long postsId) {
        PostsResponse PostsResponse = postsService.getPosts(postsId);
        return new ResponseEntity<>(PostsResponse , HttpStatus.OK);
    }

    @GetMapping(value = "/tags/{name}")
    @ApiOperation(value = "태그별 게시글 목록 조회")
    public ResponseEntity<Page<PostsResponse>> getPostsListByTagsId(
            @PageableDefault(size = 5 , sort = "id" , direction = Sort.Direction.DESC) final Pageable pageable,
            @PathVariable(name = "name") String name) {
        Page<PostsResponse> postsResponseList = postsService.getPostsListByTagsId(pageable , name);
        return new ResponseEntity<>(postsResponseList , HttpStatus.OK);
    }

    @GetMapping(value = "/latest")
    @ApiOperation(value = "최근 게시물 조회")
    public ResponseEntity<List<PostsResponse>> getLatestPosts() {
        return new ResponseEntity<>(postsService.getLatestPosts() , HttpStatus.OK);
    }


    @GetMapping(value = "/search/{keyword}")
    @ApiOperation(value = "게시글 검색")
    public ResponseEntity<Page<PostsResponse>> getPostsListByKeyword(
            @PageableDefault(size = 5 , sort = "id" , direction = Sort.Direction.DESC) final Pageable pageable ,
            @PathVariable(name = "keyword") String keyword) {

        Page<PostsResponse> postsList = postsService.searchAll(pageable , keyword);
        return new ResponseEntity<>(postsList , HttpStatus.OK);
    }

    @GetMapping(value = "/archive")
    @ApiOperation(value = "전체 조회(archive)")
    public ResponseEntity<List<ArchiveByYear>> getPostsAll() {
        return new ResponseEntity<>(postsService.getPostsArchive() , HttpStatus.OK);
    }
}
