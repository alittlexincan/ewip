package com.hyt.server.service.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.RiskSlope;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-陡坡接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
public interface IRiskSlopeService extends IBaseService<RiskSlope>{

        PageInfo<RiskSlope> selectAll(Map<String, Object> map);

        List<RiskSlope> selectList(Map<String, Object> map);

}
