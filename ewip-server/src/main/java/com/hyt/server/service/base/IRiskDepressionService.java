package com.hyt.server.service.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.RiskDepression;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-洼地接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
public interface IRiskDepressionService extends IBaseService<RiskDepression>{

        PageInfo<RiskDepression> selectAll(Map<String, Object> map);

        List<RiskDepression> selectList(Map<String, Object> map);

}
