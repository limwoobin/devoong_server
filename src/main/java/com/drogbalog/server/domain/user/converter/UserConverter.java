package com.drogbalog.server.domain.user.converter;

import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
            @Mapping(target = "accessToken", ignore = true),
            @Mapping(target = "refreshToken", ignore = true)
    })
    UserResponse userConverts(User user);
}
