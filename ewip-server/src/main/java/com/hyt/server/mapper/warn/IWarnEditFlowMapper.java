package com.hyt.server.mapper.warn;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.warn.WarnEditFlow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("warnEditFlowMapper")
public interface IWarnEditFlowMapper extends IBaseMapper<WarnEditFlow> {

    /**
     * 根据预警ID查询对应的预警流程
     * @param map
     * @return
     */
    List<WarnEditFlow> selectFlowByWarnEditId(Map<String, Object> map);

    /**
     * 插入流程信息
     * @param warnEditFlow
     * @return
     */
    int insert(WarnEditFlow warnEditFlow);

    /**
     * 修改流程：流程变更时，修改对应的操作信息
     * @param map
     * @return
     */
    int updateFlow(Map<String, Object> map);

}
