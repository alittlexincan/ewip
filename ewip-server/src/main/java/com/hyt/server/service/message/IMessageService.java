package com.hyt.server.service.message;

import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.message.Message;

import java.util.Map;


/**
 * @Author: JiangXincan
 * @Description: 一键发布接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IMessageService extends IBaseService<Message> {

    /**
     * 添加一键发布相关信息
     * @param map
     * @return
     */
    Message insert(Map<String, Object> map);


}
