package com.zhxu.info.basic.repository;

import com.zhxu.info.basic.entity.UnitMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "unitMarket")
public interface UnitMarketRepository extends JpaRepository<UnitMarket, String> {
}
