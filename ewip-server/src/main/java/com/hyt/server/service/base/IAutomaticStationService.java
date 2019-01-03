package com.hyt.server.service.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.AutomaticStation;
import com.hyt.server.entity.base.UnitSchool;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxv
 * @Description:基础数据-接口层
 * @Date: Created in 15:30 2018-12-24
 * @Modified By:
 */
public interface IAutomaticStationService extends IBaseService<AutomaticStation>{

        PageInfo<AutomaticStation> selectAll(Map<String, Object> map);

        List<AutomaticStation> selectList(Map<String, Object> map);

}
