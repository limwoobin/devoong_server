package com.drogbalog.server.domain.subscribe.service;

import com.drogbalog.server.domain.subscribe.domain.entity.SubScribe;
import com.drogbalog.server.domain.subscribe.domain.response.SubScribeResponse;
import com.drogbalog.server.domain.subscribe.mapper.SubscribeMapper;
import com.drogbalog.server.domain.subscribe.repository.SubScribeRepository;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.exception.DuplicateDataException;
import com.drogbalog.server.global.exception.messages.DuplicateExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubScribeService {
    private final SubScribeRepository subScribeRepository;
    private final SubscribeMapper subscribeMapper;

    @Transactional(readOnly = true)
    public List<SubScribeResponse> getSubscribeList() {
        List<SubScribe> subScribes = subScribeRepository.findAllByStatus(Status.ACTIVE);
        return subscribeMapper.toSubScribeResponseList(subScribes);
    }

    @Transactional
    public SubScribeResponse subscribe(String email) {
        SubScribe subScribe = subScribeRepository.findByEmail(email)
                .orElseThrow(() -> new DuplicateDataException(DuplicateExceptionType.ALREADY_EXISTS_SUBSCRIBER));

        return subscribeMapper.toSubScribeResponse(subScribe);
    }

    @Transactional
    public void unSubscribe(String email) {
        SubScribe subScribe = subScribeRepository.findByEmail(email)
                .orElseThrow(() -> new DuplicateDataException(DuplicateExceptionType.ALREADY_EXISTS_SUBSCRIBER));

        subScribeRepository.delete(subScribe);
    }
}
