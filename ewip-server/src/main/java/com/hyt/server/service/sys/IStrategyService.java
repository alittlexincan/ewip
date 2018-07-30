package com.hyt.server.service.sys;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.sys.Strategy;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 地区管理接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IStrategyService extends IBaseService<Strategy> {

    PageInfo<Strategy> selectAll(Map<String, Object> map);

    Strategy selectById(String id);

}
