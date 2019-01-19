package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitReservoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitReservoir")
public interface UnitReservoirRepository extends JpaRepository<UnitReservoir, String> {
}
