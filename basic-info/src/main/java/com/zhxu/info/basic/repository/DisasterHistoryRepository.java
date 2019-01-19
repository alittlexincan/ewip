package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.DisasterHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "disasterHistory")
public interface DisasterHistoryRepository extends JpaRepository<DisasterHistory, String> {
}
