package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.RiskForest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "riskForest")
public interface RiskForestRepository extends JpaRepository<RiskForest, String> {
}
