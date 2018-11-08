package com.hyt.server.service.impl.ueditor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.ueditor.Ueditor;
import com.hyt.server.mapper.ueditor.IServerProductMapper;
import com.hyt.server.service.ueditor.IServerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lxv
 * @Date: 2018/11/2 11:03
 * @Description:
 */
@Service("serverProductService")
public class ServerProductServiceImpl extends AbstractService<Ueditor> implements IServerProductService {
    @Autowired
    private IServerProductMapper serverProductMapper;

    /**
     * 查询所有产品
     * @param map
     * @return
     */
    @Override
    public PageInfo<Ueditor> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<Ueditor> list = this.serverProductMapper.findAll(map);
        return new PageInfo<>(list);
    }
}
