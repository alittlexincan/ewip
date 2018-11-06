package com.hyt.server.service.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.DisasterHistory;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-历史灾情接口层
 * @Date: Created in 15:30 2018-11-1
 * @Modified By:
 */
public interface IDisasterHistoryService extends IBaseService<DisasterHistory>{

        PageInfo<DisasterHistory> selectAll(Map<String, Object> map);

        List<DisasterHistory> selectList(Map<String, Object> map);

}
