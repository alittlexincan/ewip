package com.hyt.server.service.info;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.info.BiInfo;
import com.hyt.server.entity.info.VfInfo;

import java.util.List;
import java.util.Map;

/**
 * @Description:基础数据-接口层
 * @Modified By:
 */
public interface IVfInfoService extends IBaseService<VfInfo>{

        PageInfo<VfInfo> selectAll(Map<String, Object> map);

        List<VfInfo> selectList(Map<String, Object> map);

        JSONObject importData(Map<String, Object> map, List<Map<String, Object>> list);

}
