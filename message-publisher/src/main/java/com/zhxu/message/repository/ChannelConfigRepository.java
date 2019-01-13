package com.zhxu.message.repository;

import com.zhxu.message.entity.ChannelConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelConfigRepository extends JpaRepository<ChannelConfig, String> {
    Optional<ChannelConfig> findByAreaIdAndChannelType(String areaId, String type);
}
