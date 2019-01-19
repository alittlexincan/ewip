package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitPlantArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitPlantArea")
public interface UnitPlantAreaRepository extends JpaRepository<UnitPlantArea, String> {
}
