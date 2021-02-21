package com.drogbalog.server.domain.posts.api;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Created by Drogba on 2021-02-10
 * github : https://github.com/limwoobin
 */

@Data
public class TestVO {

    @Size(min = 2 , max = 5 , message = "failed zz")
    private String test;
}
