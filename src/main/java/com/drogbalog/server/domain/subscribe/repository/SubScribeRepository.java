package com.drogbalog.server.domain.subscribe.repository;

import com.drogbalog.server.domain.subscribe.domain.entity.SubScribe;
import com.drogbalog.server.global.code.Status;
import com.drogbalog.server.global.config.jpa.DefaultRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubScribeRepository extends DefaultRepository<SubScribe , Long> {
    Optional<SubScribe> findByEmail(String email);
    List<SubScribe> findAllByStatus(Status status);
}
