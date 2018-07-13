package com.hyt.server.mapper.sys;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.sys.ZTree;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * ZTree树数据访问层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("zTreeMapper")
public interface IZTreeMapper extends IBaseMapper<ZTree> {

    List<ZTree> getAreaTree(Map<String, Object> map);

}
