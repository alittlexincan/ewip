package com.hyt.server.service.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.RiskForest;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-森林火险灾害点接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
public interface IRiskForestService extends IBaseService<RiskForest>{

        PageInfo<RiskForest> selectAll(Map<String, Object> map);

}
