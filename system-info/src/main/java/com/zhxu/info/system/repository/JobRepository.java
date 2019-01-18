package com.zhxu.info.system.repository;

import com.zhxu.info.system.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, String> {
    List<Job> findAllByUserId(String userId);
}
