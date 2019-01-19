package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.RiskSlope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "riskSlope")
public interface RiskSlopeRepository extends JpaRepository<RiskSlope, String> {
}
