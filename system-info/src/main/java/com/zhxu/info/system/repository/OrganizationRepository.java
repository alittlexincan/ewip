package com.zhxu.info.system.repository;

import com.zhxu.info.system.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
}
