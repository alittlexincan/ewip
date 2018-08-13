package com.hyt.server.service.impl.warn;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.warn.WarnEditOption;
import com.hyt.server.mapper.warn.IWarnEditFlowMapper;
import com.hyt.server.mapper.warn.IWarnEditMapper;
import com.hyt.server.mapper.warn.IWarnEditOptionMapper;
import com.hyt.server.service.warn.IWarnEditOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: XincanJiang
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("warnEditOptionService")
public class WarnEditOptionServiceImpl extends AbstractService<WarnEditOption> implements IWarnEditOptionService {

    @Autowired
    private IWarnEditOptionMapper warnEditOptionMapper;

    @Autowired
    private IWarnEditFlowMapper warnEditFlowMapper;

    @Autowired
    private IWarnEditMapper warnEditMapper;

    /**
     * 根据条件查询预警编辑流程信息
     * @param map
     * @return
     */
    @Override
    public PageInfo<WarnEditOption> selectFlowByParam(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<WarnEditOption> areaList = this.warnEditOptionMapper.selectFlowByParam(map);
        return new PageInfo<>(areaList);
    }

    /**
     * 添加流程信息
     * warnEditFlow
     * @param map
     * @return
     */
    @Override
    @Transactional
    public int insert(Map<String, Object> map) {

        JSONObject json = new JSONObject(map);
        System.out.println(json);
        this.warnEditFlowMapper.updateFlow(map);

        // 修改小于或等于当前流程状态为1
        Map<String, Object> option = new HashMap<>();
        option.put("id", map.get("warnEditId"));
        option.put("flow", map.get("currentFlow"));
        option.put("isOption",1);
        this.warnEditFlowMapper.updateOption(option);

        // 如果status==1说明发布中心发布了此预警，此时更改预警状态为已发布
        if (Integer.parseInt(map.get("currentFlow").toString()) == 4) {
            map.put("id", map.get("warnEditId"));
            map.put("status", 1);
            this.warnEditMapper.updateStatus(map);
        }
        return 1;
    }

    /**
     * 修改预警状态
     *
     * @param map
     * @return
     */
    @Override
    public int updateStatus(Map<String, Object> map) {
        return this.warnEditMapper.updateStatus(map);
    }
}
