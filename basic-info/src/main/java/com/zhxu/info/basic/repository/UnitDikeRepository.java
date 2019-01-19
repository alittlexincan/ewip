package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitDike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitDike")
public interface UnitDikeRepository extends JpaRepository<UnitDike, String> {
}
