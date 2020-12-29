package com.drogbalog.server.global.config.jpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface PagingAndSortRepository<E , I> extends PagingAndSortingRepository<E , I> , JpaSpecificationExecutor<E> {
}
