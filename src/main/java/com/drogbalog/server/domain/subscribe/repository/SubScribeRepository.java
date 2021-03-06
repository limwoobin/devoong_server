package com.drogbalog.server.domain.subscribe.repository;

import com.drogbalog.server.domain.subscribe.domain.entity.SubScribe;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubScribeRepository extends DefaultRepository<SubScribe , Long> {
    SubScribe findByEmail(String email);
    List<SubScribe> findAllByStatus(Status status);
}
