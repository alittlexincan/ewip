package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.FacilityPublish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "facilityPublish")
public interface FacilityPublishRepository extends JpaRepository<FacilityPublish, String> {
}
