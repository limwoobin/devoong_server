package com.drogbalog.server.user.converter;

import com.drogbalog.server.user.domain.dto.UserDto;
import com.drogbalog.server.user.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
            @Mapping(target = "accessToken", ignore = true),
            @Mapping(target = "refreshToken", ignore = true)
    })
    UserDto userConverts(UserEntity userEntity);
}
