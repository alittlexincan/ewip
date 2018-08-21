package com.hyt.server.service.warn;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.warn.WarnEditOption;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 预警编辑操作接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IWarnEditOptionService extends IBaseService<WarnEditOption> {

    /**
     * 根据条件查询预警编辑流程信息
     * @param map
     * @return
     */
    PageInfo<WarnEditOption> selectFlowByParam(Map<String, Object> map);

    /**
     * 添加预警流程信息
     * @param map
     * @return
     */
    JSONObject insert(Map<String, Object> map);

    /**
     * 修改预警状态
     * @param map
     * @return
     */
    int updateStatus(Map<String, Object> map);

}