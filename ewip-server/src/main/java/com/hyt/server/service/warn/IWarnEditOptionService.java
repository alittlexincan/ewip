package com.hyt.server.service.warn;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.warn.WarnEdit;
import com.hyt.server.entity.warn.WarnEditFlow;
import com.hyt.server.entity.warn.WarnEditOption;
import java.util.List;
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
     * 查询预警发布后详细信息
     * @param map
     * @return
     */
    JSONObject selectWarnEditDetail(Map<String, Object> map);

    /**
     * 添加预警流程信息
     * @param map
     * @return
     */
    JSONObject insert(Map<String, Object> map);

    /**
     * 预警驳回
     * @param map
     * @return
     */
    int reject(Map<String, Object> map);


    /**
     * 根据预警ID查询对应的预警流程
     * @param map
     * @return
     */
    List<WarnEditFlow> selectFlowByWarnEditId(Map<String, Object> map);


    /**
     * 微信提供预警发布信息接口
     * @return
     */
    List<Map<String, Object>> getWechatWarnInfo();

    /**
     * 系统主页获取预警信息
     * @return
     */
    List<WarnEdit> getHomeWarnInfo(Map<String, Object> map);

}