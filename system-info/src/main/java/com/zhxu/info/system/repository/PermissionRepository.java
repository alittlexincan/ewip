package com.zhxu.info.system.repository;

import com.zhxu.info.system.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
