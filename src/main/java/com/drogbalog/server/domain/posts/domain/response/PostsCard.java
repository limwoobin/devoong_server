package com.drogbalog.server.domain.posts.domain.response;

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
    private String name;
    private String bannerImage;
}
