package com.hyt.server.mapper.message;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.message.MessageUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 一键发布受众数据接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("messageUserMapper")
public interface IMessageUserMapper extends IBaseMapper<MessageUser> {

    int insertList(List<MessageUser> list);

    List<MessageUser> selectByMessageId(Map<String, Object> map);
}
