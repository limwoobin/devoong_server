package com.drogbalog.server.domain.posts.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Archive {
    private Long id;
    private String title;
    private String createdYear;
    private String createdDate;
}
