package com.hyt.server.service.info;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.info.BiInfo;
import com.hyt.server.entity.info.PmsInfo;

import java.util.List;
import java.util.Map;

/**
 * @Description:基础数据-接口层
 * @Modified By:
 */
public interface IBiInfoService extends IBaseService<BiInfo>{

        PageInfo<BiInfo> selectAll(Map<String, Object> map);

        List<BiInfo> selectList(Map<String, Object> map);

        JSONObject importData(Map<String, Object> map, List<Map<String, Object>> list);

}
