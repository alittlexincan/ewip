package com.zxyt.ocpp.client.mapper.warn;

import com.zxyt.ocpp.client.config.common.universal.IBaseMapper;
import com.zxyt.ocpp.client.entity.message.MessageUser;
import com.zxyt.ocpp.client.entity.warn.WarnEditContent;
import com.zxyt.ocpp.client.entity.warn.WarnEditUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("warnEditUserMapper")
public interface IWarnEditUserMapper extends IBaseMapper<WarnEditUser> {

    @Override
    int insertList(List<WarnEditUser> list);

    List<WarnEditUser> selectByWarnEditId(Map<String, Object> map);
}
