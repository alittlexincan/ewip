package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.DisasterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "disasterType")
public interface DisasterTypeRepository extends JpaRepository<DisasterType, String> {
}
