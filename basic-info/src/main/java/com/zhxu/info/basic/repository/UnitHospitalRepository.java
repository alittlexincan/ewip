package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitHospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitHospital")
public interface UnitHospitalRepository extends JpaRepository<UnitHospital, String> {
}
