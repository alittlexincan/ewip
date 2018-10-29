package com.hyt.server.mapper.warn;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.warn.WarnEditFile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("warnEditFileMapper")
public interface IWarnEditFileMapper extends IBaseMapper<WarnEditFile> {

    int insertList(List<WarnEditFile> list);

    /**
     * 根据id查询当前预警上传文件信息
     * @param map
     * @return
     */
    List<WarnEditFile> selectByWarnEditId(Map<String, Object> map);

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
}
