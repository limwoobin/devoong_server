package com.drogbalog.server.domain.user.mapper;

import com.drogbalog.server.domain.user.domain.response.UserResponse;
import com.drogbalog.server.domain.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "jwtResponse" , ignore = true)
    UserResponse toUserResponse(User user);
}
