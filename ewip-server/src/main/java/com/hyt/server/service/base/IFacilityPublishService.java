package com.hyt.server.service.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.FacilityPublish;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-发布设施接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
public interface IFacilityPublishService extends IBaseService<FacilityPublish>{

        PageInfo<FacilityPublish> selectAll(Map<String, Object> map);

}
