package com.drogbalog.server.domain.subscribe.dao;

import com.drogbalog.server.domain.subscribe.converter.SubscribeConverter;
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
    private final SubscribeConverter subscribeConverter;

    @Transactional
    public List<SubScribeResponse> getSubscribeList() {
        List<SubScribe> subScribes = subScribeRepository.findAllByStatus(Status.ACTIVE);
        return subscribeConverter.convertList(subScribes);
    }

    @Transactional
    public SubScribeResponse findByEmail(String email) {
        SubScribe subScribe = subScribeRepository.findByEmail(email);
        return subscribeConverter.converts(subScribe);
    }

    @Transactional
    public SubScribeResponse subscribe(String email) {
        SubScribe subScribe = SubScribe.builder()
                .email(email)
                .build();

        subScribe = subScribeRepository.save(subScribe);
        return subscribeConverter.converts(subScribe);
    }

    @Transactional
    public void unSubscribe(String email) {
        SubScribe subScribe = subScribeRepository.findByEmail(email);
        subScribeRepository.delete(subScribe);
    }
}
