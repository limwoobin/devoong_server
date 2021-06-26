package com.drogbalog.server.domain.subscribe.mapper;

import com.drogbalog.server.domain.subscribe.domain.entity.SubScribe;
import com.drogbalog.server.domain.subscribe.domain.response.SubScribeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscribeMapper {
    SubscribeMapper INSTANCE = Mappers.getMapper(SubscribeMapper.class);

    SubScribeResponse toSubScribeResponse(SubScribe subScribe);
    List<SubScribeResponse> toSubScribeResponseList(List<SubScribe> subScribeList);
}
