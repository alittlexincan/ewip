package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.DisasterRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "disasterRoute")
public interface DisasterRouteRepository extends JpaRepository<DisasterRoute, String> {
}
