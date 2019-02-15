package com.zxyt.ocpp.client.mapper.warn;

import com.zxyt.ocpp.client.config.common.universal.IBaseMapper;
import com.zxyt.ocpp.client.entity.warn.WarnEdit;
import org.springframework.stereotype.Repository;

@Repository("warnEditMapper")
public interface IWarnEditMapper extends IBaseMapper<WarnEdit> {
}
