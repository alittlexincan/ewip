package com.hyt.server.service.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.UnitAttractions;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-旅游景区接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
public interface IUnitAttractionsService extends IBaseService<UnitAttractions>{

        PageInfo<UnitAttractions> selectAll(Map<String, Object> map);

        List<UnitAttractions> selectList(Map<String, Object> map);

}
