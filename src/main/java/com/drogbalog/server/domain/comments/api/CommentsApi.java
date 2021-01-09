package com.drogbalog.server.domain.comments.api;

import com.drogbalog.server.domain.comments.domain.entity.SubComments;
import com.drogbalog.server.domain.comments.domain.request.CommentsRequest;
import com.drogbalog.server.domain.comments.domain.request.SubCommentsRequest;
import com.drogbalog.server.domain.comments.domain.response.CommentsResponse;
import com.drogbalog.server.domain.comments.domain.response.SubCommentsResponse;
import com.drogbalog.server.domain.comments.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsApi {
    private final CommentsService commentsService;

    @GetMapping(value = "/posts/{postsId}")
    public ResponseEntity<List<CommentsResponse>> getCommentsByPosts(@PathVariable long postsId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<CommentsResponse>> getCommentsByUser(@PathVariable long userId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{postsId}")
    public ResponseEntity<CommentsResponse> create(@PathVariable long postsId , @Valid @RequestBody CommentsRequest request) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/{commentsId}")
    private ResponseEntity<SubCommentsResponse> createSubComments(@PathVariable long commentsId , @Valid @RequestBody SubCommentsRequest subCommentsRequest) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CommentsResponse> update(@PathVariable long id , @Valid @RequestBody CommentsRequest request) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CommentsResponse> delete(@PathVariable long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
