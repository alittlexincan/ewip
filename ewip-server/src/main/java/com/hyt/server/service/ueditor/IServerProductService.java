package com.hyt.server.service.ueditor;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.ueditor.Ueditor;

import java.util.Map;

/**
 * @Auther: lxv
 * @Date: 2018/11/2 11:03
 * @Description:
 */
public interface IServerProductService extends IBaseService<Ueditor> {

    PageInfo<Ueditor> selectAll(Map<String, Object> map);

}
