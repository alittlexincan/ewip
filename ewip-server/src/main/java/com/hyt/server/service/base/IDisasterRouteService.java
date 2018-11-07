package com.hyt.server.service.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.DisasterRoute;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-气象灾害影响路径和范围接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
public interface IDisasterRouteService extends IBaseService<DisasterRoute>{

        PageInfo<DisasterRoute> selectAll(Map<String, Object> map);
        
        List<DisasterRoute> selectList(Map<String, Object> map);

}
