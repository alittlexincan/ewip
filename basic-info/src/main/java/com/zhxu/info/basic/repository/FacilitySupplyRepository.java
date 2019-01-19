package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.FacilitySupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "facilitySupply")
public interface FacilitySupplyRepository extends JpaRepository<FacilitySupply, String> {
}
