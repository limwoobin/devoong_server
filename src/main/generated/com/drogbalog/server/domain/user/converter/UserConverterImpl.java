package com.drogbalog.server.domain.user.converter;

import com.drogbalog.server.domain.user.domain.entity.User;
import com.drogbalog.server.domain.user.domain.response.UserResponse;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-05T15:21:52+0900",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public UserResponse userConverts(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setNickname( user.getNickname() );
        userResponse.setImageUri( user.getImageUri() );
        userResponse.setGender( user.getGender() );
        userResponse.setStatus( user.getStatus() );

        return userResponse;
    }
}
