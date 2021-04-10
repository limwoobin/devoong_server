package com.drogbalog.server.domain.subscribe.dao;

import com.drogbalog.server.domain.subscribe.mapper.SubscribeMapper;
import com.drogbalog.server.domain.subscribe.domain.entity.SubScribe;
import com.drogbalog.server.domain.subscribe.domain.response.SubScribeResponse;
import com.drogbalog.server.domain.subscribe.repository.SubScribeRepository;
import com.drogbalog.server.global.code.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubScribeDao {
    private final SubScribeRepository subScribeRepository;
    private final SubscribeMapper subscribeMapper;

    @Transactional
    public List<SubScribeResponse> getSubscribeList() {
        List<SubScribe> subScribes = subScribeRepository.findAllByStatus(Status.ACTIVE);
        return subscribeMapper.toSubScribeResponseList(subScribes);
    }

    @Transactional
    public SubScribeResponse findByEmail(String email) {
        SubScribe subScribe = subScribeRepository.findByEmail(email);
        return subscribeMapper.toSubScribeResponse(subScribe);
    }

    @Transactional
    public SubScribeResponse subscribe(String email) {
        SubScribe subScribe = SubScribe.builder()
                .email(email)
                .build();

        subScribe = subScribeRepository.save(subScribe);
        return subscribeMapper.toSubScribeResponse(subScribe);
    }

    @Transactional
    public void unSubscribe(String email) {
        SubScribe subScribe = subScribeRepository.findByEmail(email);
        subScribeRepository.delete(subScribe);
    }
}
