package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitDanger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitDanger")
public interface UnitDangerRepository extends JpaRepository<UnitDanger, String> {
}
