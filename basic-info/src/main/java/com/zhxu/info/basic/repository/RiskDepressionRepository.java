package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.RiskDepression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "riskDepression")
public interface RiskDepressionRepository extends JpaRepository<RiskDepression, String> {
}
