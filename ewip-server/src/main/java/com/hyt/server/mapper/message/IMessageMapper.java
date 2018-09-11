package com.hyt.server.mapper.message;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.message.Message;
import org.springframework.stereotype.Repository;


/**
 * 一键发布基础数据接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("messageMapper")
public interface IMessageMapper extends IBaseMapper<Message> {

}
