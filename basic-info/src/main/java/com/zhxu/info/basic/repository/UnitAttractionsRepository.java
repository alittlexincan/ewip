package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitAttractions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitAttractions")
public interface UnitAttractionsRepository extends JpaRepository<UnitAttractions, String> {
}
