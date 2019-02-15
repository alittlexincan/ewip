package com.zxyt.ocpp.client.mapper.warn;

import com.zxyt.ocpp.client.config.common.universal.IBaseMapper;
import com.zxyt.ocpp.client.entity.warn.WarnEditContent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("warnEditContentMapper")
public interface IWarnEditContentMapper extends IBaseMapper<WarnEditContent> {

    @Override
    int insertList(List<WarnEditContent> list);

}
