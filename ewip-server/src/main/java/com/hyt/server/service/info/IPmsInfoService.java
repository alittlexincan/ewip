package com.hyt.server.service.info;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.info.PmsInfo;

import java.util.List;
import java.util.Map;

/**
 * @Description:基础数据-接口层
 * @Modified By:
 */
public interface IPmsInfoService extends IBaseService<PmsInfo>{

        PageInfo<PmsInfo> selectAll(Map<String, Object> map);

        List<PmsInfo> selectList(Map<String, Object> map);

        JSONObject importData(Map<String,Object> map, List<Map<String,Object>> list);

}
