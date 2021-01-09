package com.drogbalog.server.domain.comments.converter;

import com.drogbalog.server.domain.comments.domain.entity.Comments;
import com.drogbalog.server.domain.comments.domain.response.CommentsResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentsConverter {
    List<CommentsResponse> convertComments(List<Comments> commentsList);
}
