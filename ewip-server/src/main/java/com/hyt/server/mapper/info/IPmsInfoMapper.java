package com.hyt.server.mapper.info;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.info.PmsInfo;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


@Repository("pmsInfoMapper")
public interface IPmsInfoMapper extends IBaseMapper<PmsInfo> {
    /**
     * 分页查询信息
     * @param map
     * @return
     */
    List<PmsInfo> findAll(Map<String, Object> map);

    int importData(List<PmsInfo> listNew);
}