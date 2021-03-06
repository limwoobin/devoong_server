package com.drogbalog.server.domain.subscribe.converter;

import com.drogbalog.server.domain.subscribe.domain.entity.SubScribe;
import com.drogbalog.server.domain.subscribe.domain.response.SubScribeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscribeConverter {
    SubScribeResponse converts(SubScribe subScribe);
    List<SubScribeResponse> convertList(List<SubScribe> subScribeList);
}
