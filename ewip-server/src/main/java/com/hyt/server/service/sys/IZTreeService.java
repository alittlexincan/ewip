package com.hyt.server.service.sys;

import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.sys.ZTree;

import java.util.List;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: ZTree树接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IZTreeService extends IBaseService<ZTree> {

    List<ZTree> getAreaTree(Map<String, Object> map);

}
