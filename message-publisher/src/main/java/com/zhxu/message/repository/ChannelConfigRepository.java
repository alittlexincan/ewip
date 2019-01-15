package com.zhxu.message.repository;

import com.zhxu.message.entity.ChannelConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelConfigRepository extends JpaRepository<ChannelConfig, String> {
    ChannelConfig findByAreaIdAndChannelType(String areaId, String type);
}
