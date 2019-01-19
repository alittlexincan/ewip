package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitHighway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitHighway")
public interface UnitHighwayRepository extends JpaRepository<UnitHighway, String> {
}
