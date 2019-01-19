package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.FacilityShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "facilityShelter")
public interface FacilityShelterRepository extends JpaRepository<FacilityShelter, String> {
}
