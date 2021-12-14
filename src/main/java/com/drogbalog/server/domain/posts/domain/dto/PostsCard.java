package com.drogbalog.server.domain.posts.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PostsCard {
    private Long id;
    private String title;
    private String bannerImage;
}
