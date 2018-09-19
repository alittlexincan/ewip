package com.hyt.server.mapper.message;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.message.MessageFile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("messageFileMapper")
public interface IMessageFileMapper extends IBaseMapper<MessageFile> {

    int insertList(List<MessageFile> list);

    /**
     * 根据id查询当前一键发布上传文件信息
     * @param map
     * @return
     */
    List<MessageFile> selectByMessageId(Map<String, Object> map);

}
