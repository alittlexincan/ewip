package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.RiskGeologic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "riskGeologic")
public interface RiskGeologicRepository extends JpaRepository<RiskGeologic, String> {
}
