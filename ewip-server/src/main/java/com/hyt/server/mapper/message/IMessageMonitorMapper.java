package com.hyt.server.mapper.message;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.message.MessageMonitor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 一键发布基础数据接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("messageMonitorMapper")
public interface IMessageMonitorMapper  extends IBaseMapper<MessageMonitor> {

    /**
     * 统计一键发布列表信息
     * @param map
     * @return
     */
    List<MessageMonitor> findMessageMonitor(Map<String, Object> map);

    /**
     * 根据一键发布类型进行统计（饼图）
     * @param map
     * @return
     */
    List<MessageMonitor> findMessageTypeTotal(Map<String, Object> map);

    /**
     * 根据一键发布渠道进行统计（柱状图）
     * @param map
     * @return
     */
    List<MessageMonitor> findMessageChannelTotal(Map<String, Object> map);
}
