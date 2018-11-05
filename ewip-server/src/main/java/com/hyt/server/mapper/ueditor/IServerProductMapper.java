package com.hyt.server.mapper.ueditor;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.sys.Area;
import com.hyt.server.entity.ueditor.Ueditor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


/**
 * @Auther: lxv
 * @Date: 2018/11/2 11:03
 * @Description:
 */
@Repository("serverProductMapper")
public interface IServerProductMapper extends IBaseMapper<Ueditor> {

    /**
     * 分页查询信息
     * @param map
     * @return
     */
    List<Ueditor> findAll(Map<String, Object> map);

}
