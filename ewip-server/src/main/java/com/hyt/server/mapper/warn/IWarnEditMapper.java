package com.hyt.server.mapper.warn;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.message.Message;
import com.hyt.server.entity.warn.WarnEdit;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("warnEditMapper")
public interface IWarnEditMapper extends IBaseMapper<WarnEdit> {

    List<WarnEdit> findAll(Map<String, Object> map);

    /**
     * 修改预警状态
     * @param map
     * @return
     */
    int updateStatus(Map<String, Object> map);

    /**
     * 根据id查询当前预警基本信息
     * @param map
     * @return
     */
    WarnEdit selectByWarnEditId(Map<String, Object> map);

}
