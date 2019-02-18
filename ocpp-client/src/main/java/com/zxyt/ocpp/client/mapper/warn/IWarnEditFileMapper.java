package com.zxyt.ocpp.client.mapper.warn;

import com.zxyt.ocpp.client.config.common.universal.IBaseMapper;
import com.zxyt.ocpp.client.entity.warn.WarnEditFile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("warnEditFileMapper")
public interface IWarnEditFileMapper extends IBaseMapper<WarnEditFile> {

    @Override
    int insertList(List<WarnEditFile> list);

}
