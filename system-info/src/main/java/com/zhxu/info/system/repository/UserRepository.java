package com.zhxu.info.system.repository;


import com.zhxu.info.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
