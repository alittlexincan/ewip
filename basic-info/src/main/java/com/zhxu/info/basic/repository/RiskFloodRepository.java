package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.RiskFlood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "riskFlood")
public interface RiskFloodRepository extends JpaRepository<RiskFlood, String> {
}
