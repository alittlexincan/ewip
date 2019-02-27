package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.RealTimeDisasterFile;
import com.hyt.server.entity.warn.WarnEditFile;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


/**
 * @Author: lxv
 * @Description:
 * @Modified By:
 */
@Repository("realTimeDisasterFileMapper")
public interface IRealTimeDisasterFileMapper extends IBaseMapper<RealTimeDisasterFile> {

    /**
     * 根据id查询当前预警上传文件信息
     * @param map
     * @return
     */
    List<RealTimeDisasterFile> selectByWarnEditId(Map<String, Object> map);

    /**
     * 根据预警编辑ID，删除预警上传文件信息
     * @param map
     * @return
     */
    int deleteFileByWarnEditId(Map<String, Object> map);

    /**
     * 将老的预警文件信息更新到新的预警文件列表中
     * @param map
     * @return
     */
    int updateOldFileByNewEditId(Map<String, Object> map);

    int insertList(List<RealTimeDisasterFile> list);
}
