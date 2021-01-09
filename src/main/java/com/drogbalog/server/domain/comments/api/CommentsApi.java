package com.drogbalog.server.domain.comments.api;

import com.drogbalog.server.domain.comments.domain.request.CommentsRequest;
import com.drogbalog.server.domain.comments.domain.request.SubCommentsRequest;
import com.drogbalog.server.domain.comments.domain.response.CommentsResponse;
import com.drogbalog.server.domain.comments.domain.response.SubCommentsResponse;
import com.drogbalog.server.domain.comments.service.CommentsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.drogbalog.server.global.util.StaticInfo.DR_HEADER_TOKEN;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Log4j2
public class CommentsApi {
    private final CommentsService commentsService;

    @GetMapping(value = "/posts/{postsId}")
    @ApiOperation(value = "게시글 댓글 조회")
    public ResponseEntity<List<CommentsResponse>> getCommentsByPosts(@PathVariable long postsId) {
        List<CommentsResponse> commentsResponseList = commentsService.getCommentsByPosts(postsId);
        return new ResponseEntity<>(commentsResponseList , HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    @ApiOperation(value = "사용자 댓글 조회")
    public ResponseEntity<List<CommentsResponse>> getCommentsByUser(@PathVariable long userId) {
        List<CommentsResponse> commentsResponseList = commentsService.getCommentsByUser(userId);
        return new ResponseEntity<>(commentsResponseList , HttpStatus.OK);
    }

    @PostMapping(value = "/{postsId}")
    @ApiOperation(value = "게시글 댓글 등록")
    public ResponseEntity<CommentsResponse> create(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token ,
            @PathVariable long postsId , @Valid @RequestBody CommentsRequest request) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/{commentsId}")
    @ApiOperation(value = "하위 댓글 등록")
    private ResponseEntity<SubCommentsResponse> createSubComments(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token ,
            @PathVariable long commentsId , @Valid @RequestBody SubCommentsRequest request) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "댓글 수정")
    public ResponseEntity<CommentsResponse> update(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token ,
            @PathVariable long id , @Valid @RequestBody CommentsRequest request) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/sub/{id}")
    @ApiOperation(value = "하위 댓글 수정")
    public ResponseEntity<SubCommentsResponse> updateSubComments(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token ,
            @PathVariable long id , @Valid @RequestBody SubCommentsRequest request) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token , @PathVariable long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/sub/{id}")
    public ResponseEntity<Void> deleteSubComments(
            @RequestHeader(value = DR_HEADER_TOKEN , defaultValue = "") String token , @PathVariable long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
