package com.drogbalog.server.comment.controller;

import com.drogbalog.server.comment.domain.dto.CommentDto;
import com.drogbalog.server.comment.domain.request.CommentRequestVo;
import com.drogbalog.server.comment.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Api(tags = "Comment Api")
public class CommentController {
    private final CommentService commentService;

    private static final Logger logger = LogManager.getLogger(CommentController.class);

    @PostMapping(value = "/post")
    @ApiOperation(value = "코멘트 등록")
    public ResponseEntity<CommentDto> postComment(@RequestBody CommentRequestVo commentRequestVo) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit")
    @ApiOperation(value = "코멘트 수정")
    public ResponseEntity<CommentDto> editComment(@RequestBody CommentRequestVo commentRequestVo) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
