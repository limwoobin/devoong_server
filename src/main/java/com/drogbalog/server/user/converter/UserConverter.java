package com.drogbalog.server.user.converter;

import com.drogbalog.server.user.domain.dto.UserDto;
import com.drogbalog.server.user.domain.entity.UserEntity;
import com.drogbalog.server.user.domain.request.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter {

    UserDto userConverts(UserEntity userEntity);

    UserEntity userRequestConvert(UserRequest request);
}
