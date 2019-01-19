package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitStation")
public interface UnitStationRepository extends JpaRepository<UnitStation, String> {
}
