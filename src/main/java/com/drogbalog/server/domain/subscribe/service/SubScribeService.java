package com.drogbalog.server.domain.subscribe.service;

import com.drogbalog.server.domain.subscribe.dao.SubScribeDao;
import com.drogbalog.server.domain.subscribe.domain.response.SubScribeResponse;
import com.drogbalog.server.global.exception.DuplicateDataException;
import com.drogbalog.server.global.exception.messages.DuplicateExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubScribeService {
    private final SubScribeDao subScribeDao;

    public List<SubScribeResponse> getSubscribeList() {
        return subScribeDao.getSubscribeList();
    }

    public SubScribeResponse subscribe(String email) {
        SubScribeResponse subScribeResponse = subScribeDao.findByEmail(email);
        if (!StringUtils.isEmpty(subScribeResponse)) {
            throw new DuplicateDataException(DuplicateExceptionType.ALREADY_EXISTS_SUBSCRIBER);
        }

        return subScribeDao.subscribe(email);
    }

    public void unSubscribe(String email) {
        SubScribeResponse subScribeResponse = subScribeDao.findByEmail(email);

        if (StringUtils.isEmpty(subScribeResponse)) {
            throw new DuplicateDataException(DuplicateExceptionType.NON_EXISTS_SUBSCRIBER);
        }

        subScribeDao.unSubscribe(email);
    }
}
