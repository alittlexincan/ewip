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

    /**
     * 根据条件分页查询预警信息
     * @param map
     * @return
     */
    List<WarnEdit> findAll(Map<String, Object> map);

    /**
     * 根据id查询当前预警基本信息
     * @param map
     * @return
     */
    WarnEdit selectByWarnEditId(Map<String, Object> map);

    /**
     * 根据预警编辑ID修改预警状态
     * @param map
     * @return
     */
    int updateStatus(Map<String, Object> map);

    /**
     * 根据预警编辑ID删除预警编辑信息
     * @param map
     * @return
     */
    int deleteByWarnEditId(Map<String, Object> map);

}
