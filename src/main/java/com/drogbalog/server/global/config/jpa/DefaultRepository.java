package com.drogbalog.server.global.config.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DefaultRepository<E , I> extends JpaRepository<E , I> , JpaSpecificationExecutor<E> {
}
