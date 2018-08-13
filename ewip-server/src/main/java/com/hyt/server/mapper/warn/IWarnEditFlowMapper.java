package com.hyt.server.mapper.warn;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.warn.WarnEditFlow;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("warnEditFlowMapper")
public interface IWarnEditFlowMapper extends IBaseMapper<WarnEditFlow> {

    int insert(WarnEditFlow warnEditFlow);

    int updateFlow(Map<String, Object> map);

    int updateOption(Map<String, Object> map);

}
