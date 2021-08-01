package com.drogbalog.server.domain.posts.domain.response;

import com.drogbalog.server.domain.posts.domain.dto.PostsCard;
import com.drogbalog.server.domain.posts.domain.dto.PostsCardList;
import com.drogbalog.server.domain.tags.domain.response.TagsResponse;
import com.drogbalog.server.global.utils.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "게시글 데이터 모델")
public class PostsResponse {

    @ApiModelProperty(value = "id")
    private long id;

    @ApiModelProperty(value = "title")
    private String title;

    @ApiModelProperty(value = "contents")
    private String contents;

    @ApiModelProperty(value = "banner image")
    private String bannerImage;

    @ApiModelProperty(value = "views")
    private Long views;

    @ApiModelProperty(value = "createdDate")
    @JsonFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    @ApiModelProperty(value = "modifiedDate")
    @JsonFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN, timezone = "Asia/Seoul")
    private LocalDateTime modifiedDate;

    @ApiModelProperty(value = "tags")
    private List<TagsResponse> tagsResponseList;

    @ApiModelProperty(value = "previous Posts")
    private PostsCard previousPostsCard;

    @ApiModelProperty(value = "next Posts")
    private PostsCard nextPostsCard;

    public void addTagsList(List<TagsResponse> tagsResponseList) {
        this.tagsResponseList = tagsResponseList;
    }

    public void addPreviousAndNextPostsCard(PostsCardList postsCardList) {
        setPreviousPostsCard(postsCardList.getPreviousPostsCard());
        setNextPostsCard(postsCardList.getNextPostsCard());
    }

    public PostsResponse(long id , String title , String contents , String bannerImage , Long views ,  LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.bannerImage = bannerImage;
        this.views = views;
        this.createdDate = createdDate;
    }

}
