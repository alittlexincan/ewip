package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitSchool")
public interface UnitSchoolRepository extends JpaRepository<UnitSchool, String> {
}
