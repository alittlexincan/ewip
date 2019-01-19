package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitAgriculturePark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitAgriculturePark")
public interface UnitAgricultureParkRepository extends JpaRepository<UnitAgriculturePark, String> {
}
