package com.hyt.server.mapper.warn;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.warn.WarnEdit;
import com.hyt.server.entity.warn.WarnEditOption;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("warnEditOptionMapper")
public interface IWarnEditOptionMapper extends IBaseMapper<WarnEditOption> {

    /**
     * 根据预警参数信息获取流程信息
     * @param map
     * @return
     */
    List<WarnEditOption> selectFlowByParam(Map<String, Object> map);

    /**
     * 给微信平台提供预警信息
     * @return
     */
    List<Map<String, Object>> getWechatWarnInfo();

    /**
     * 系统主页获取预警信息
     * @return
     */
    List<WarnEdit> getHomeWarnInfo(Map<String, Object> map);
}
