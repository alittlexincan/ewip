package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.RiskWaterLoggingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "riskWaterLoggingArea")
public interface RiskWaterLoggingAreaRepository extends JpaRepository<RiskWaterLoggingArea, String> {
}
