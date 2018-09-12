package com.hyt.server.mapper.message;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.message.MessageContent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 一键发布内容数据接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("messageContentMapper")
public interface IMessageContentMapper extends IBaseMapper<MessageContent> {

    int insertList(List<MessageContent> list);

    /**
     * 根据id查询当前一键发布内容信息
     * @param map
     * @return
     */
    List<MessageContent> selectByMessageId(Map<String, Object> map);
}
