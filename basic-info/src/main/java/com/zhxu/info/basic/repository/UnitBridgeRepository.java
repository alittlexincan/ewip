package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitBridge")
public interface UnitBridgeRepository extends JpaRepository<UnitBridge, String> {
}
