package com.hyt.server.service.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.UnitHighway;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-高速公路接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
public interface IUnitHighwayService extends IBaseService<UnitHighway>{

        PageInfo<UnitHighway> selectAll(Map<String, Object> map);

        List<UnitHighway> selectList(Map<String, Object> map);

}
