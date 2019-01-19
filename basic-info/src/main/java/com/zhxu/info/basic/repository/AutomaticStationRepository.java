package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.AutomaticStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "automaticStation")
public interface AutomaticStationRepository extends JpaRepository<AutomaticStation, String> {
}
