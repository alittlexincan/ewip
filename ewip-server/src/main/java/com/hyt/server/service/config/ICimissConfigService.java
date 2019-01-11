package com.hyt.server.service.config;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.config.CimissConfig;

import java.util.Map;

/**
 * @ClassName ICimissConfigService
 * @Description 获取cimiss配置信息
 * @Author Xincan
 * @Version 1.0
 **/
public interface ICimissConfigService extends IBaseService<CimissConfig> {
    String getRequestUrl(String areaId);

    PageInfo<CimissConfig> selectAll(Map<String, Object> map);
}
