package com.zxyt.ocpp.client.mapper.ueditor;

import com.zxyt.ocpp.client.config.common.universal.IBaseMapper;
import com.zxyt.ocpp.client.entity.ueditor.Ueditor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("ueditorMapper")
public interface IUeditorMapper extends IBaseMapper<Ueditor> {

    List<Ueditor> findAll(Map<String, Object> map);
}
