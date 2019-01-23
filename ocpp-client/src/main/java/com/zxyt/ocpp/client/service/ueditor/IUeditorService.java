package com.zxyt.ocpp.client.service.ueditor;

import com.github.pagehelper.PageInfo;
import com.zxyt.ocpp.client.config.common.universal.IBaseService;
import com.zxyt.ocpp.client.entity.ueditor.Ueditor;

import java.util.Map;

public interface IUeditorService extends IBaseService<Ueditor> {

    int insert(Map<String, Object> newMap) throws  Exception;

    PageInfo<Ueditor> selectAll(Map<String, Object> map);
}
