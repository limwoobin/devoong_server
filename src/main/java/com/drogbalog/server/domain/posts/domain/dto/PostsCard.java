package com.drogbalog.server.domain.posts.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostsCard {
    private Long id;
    private String title;
    private String bannerImage;
}
