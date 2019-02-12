package com.zxyt.ocpp.client.service.sys;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zxyt.ocpp.client.config.common.universal.IBaseService;
import com.zxyt.ocpp.client.entity.sys.WarnConfig;

import java.util.List;
import java.util.Map;

/**
 * @Author: msm
 * @Description: 预警管理接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IWarnConfigService extends IBaseService<WarnConfig> {

    PageInfo<WarnConfig> selectAll(Map<String, Object> map);

    WarnConfig selectConfig(Map<String, Object> map);
}
