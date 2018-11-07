package com.hyt.server.service.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.UnitHospital;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-医院接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
public interface IUnitHospitalService extends IBaseService<UnitHospital>{

        PageInfo<UnitHospital> selectAll(Map<String, Object> map);

}
