package com.hyt.server.mapper.warn;

import com.hyt.server.config.common.universal.IBaseMapper;
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

    List<WarnEditOption> selectFlowByParam(Map<String, Object> map);
}
