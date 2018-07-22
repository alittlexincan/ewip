package com.hyt.server.service.sys;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.sys.Disaster;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 灾种管理接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IDisasterService extends IBaseService<Disaster> {

    PageInfo<Disaster> selectAll(Map<String, Object> map);

    Disaster selectById(String id);

}
