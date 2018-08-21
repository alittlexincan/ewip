package com.hyt.server.mapper.warn;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.warn.WarnEditUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("warnEditUserMapper")
public interface IWarnEditUserMapper extends IBaseMapper<WarnEditUser> {

    int insertList(List<WarnEditUser> list);

    /**
     * 根据id查询当前预警发布对象信息
     * @param map
     * @return
     */
    List<WarnEditUser> selectByWarnEditId(Map<String, Object> map);
}
