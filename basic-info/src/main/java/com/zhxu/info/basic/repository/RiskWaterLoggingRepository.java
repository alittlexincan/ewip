package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.RiskWaterLogging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "riskWaterLogging")
public interface RiskWaterLoggingRepository extends JpaRepository<RiskWaterLogging, String> {
}
