package com.drogbalog.server.domain.posts.converter;

import com.drogbalog.server.domain.posts.domain.dto.PostsResponse;
import com.drogbalog.server.domain.posts.domain.entity.Posts;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-05T15:21:52+0900",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class PostsConverterImpl implements PostsConverter {

    @Override
    public PostsResponse convertEntity(Posts postsEntity) {
        if ( postsEntity == null ) {
            return null;
        }

        PostsResponse postsResponse = new PostsResponse();

        postsResponse.setId( postsEntity.getId() );
        postsResponse.setEmail( postsEntity.getEmail() );
        postsResponse.setSubject( postsEntity.getSubject() );
        postsResponse.setContents( postsEntity.getContents() );
        postsResponse.setViews( postsEntity.getViews() );
        postsResponse.setCreatedDate( postsEntity.getCreatedDate() );
        postsResponse.setModifiedDate( postsEntity.getModifiedDate() );

        return postsResponse;
    }
}
