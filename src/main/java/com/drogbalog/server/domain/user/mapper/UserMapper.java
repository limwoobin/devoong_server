package com.drogbalog.server.domain.user.mapper;

import com.drogbalog.server.domain.user.domain.request.UserRequest;
import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "jwtResponse" , ignore = true)
    UserResponse toUserResponse(User user);

//    @Mappings({
//            @Mapping(target = "provider", ignore = true),
//            @Mapping(target = "providerId", ignore = true)
//    })
//    User toUser(UserRequest request);
}
