package com.hyt.server.mapper.warn;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.warn.WarnEditContent;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("warnEditContentMapper")
public interface IWarnEditContentMapper extends IBaseMapper<WarnEditContent> {

    int insertList(List<WarnEditContent> list);
}
