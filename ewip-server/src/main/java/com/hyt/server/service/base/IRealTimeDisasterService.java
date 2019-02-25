package com.hyt.server.service.base;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.RealTimeDisaster;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxv
 * @Description:灾情接口层
 * @Date: Created in 15:30 2018-11-1
 * @Modified By:
 */
public interface IRealTimeDisasterService extends IBaseService<RealTimeDisaster>{

        PageInfo<RealTimeDisaster> selectAll(Map<String, Object> map);

        List<RealTimeDisaster> selectList(Map<String, Object> map);

        int insert(Map<String,Object> map);

        JSONObject selectFile(Map<String,Object> map);
}
