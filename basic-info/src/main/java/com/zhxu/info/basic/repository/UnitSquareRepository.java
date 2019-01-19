package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitSquare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitSquare")
public interface UnitSquareRepository extends JpaRepository<UnitSquare, String> {
}
