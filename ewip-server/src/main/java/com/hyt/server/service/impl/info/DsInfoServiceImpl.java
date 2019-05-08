package com.hyt.server.service.impl.info;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.info.DsInfo;
import com.hyt.server.mapper.info.IDsInfoMapper;
import com.hyt.server.service.info.IDsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("dsInfoService")
public  class DsInfoServiceImpl extends AbstractService<DsInfo> implements IDsInfoService {

    @Autowired
    private IDsInfoMapper dsInfoMapper;

    @Override
    public PageInfo<DsInfo> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<DsInfo> areaList = this.dsInfoMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<DsInfo> selectList(Map<String, Object> map){
        return this.dsInfoMapper.findAll(map);
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws Exception {
        if (map == null)
            return null;
        T obj = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        return obj;
    }

    @Override
    public JSONObject importData(Map<String, Object> map, List<Map<String,Object>> list ) {
        String reportUnitCode=map.get("organizationCode").toString();
        JSONObject json=new JSONObject();
        List<DsInfo> listNew=new ArrayList<>();
        try {
            for(Map<String, Object> mapNew: list){
                DsInfo dsInfo = null;
                dsInfo = mapToObject(mapNew,DsInfo.class);
                dsInfo.setReportUnitCode(reportUnitCode);
                System.out.println(dsInfo);
                listNew.add(dsInfo);
            }
            if(listNew.size()>0){
                int a=this.dsInfoMapper.importData(listNew);
                json.put("code","200");
                json.put("msg","导入成功！共"+listNew.size()+"条数据");
            }else {
                json.put("code","500");
                json.put("msg","导入失败，请检查文件！");
            }
        } catch (Exception e) {
            json.put("code","500");
            json.put("msg","导入失败，请检查文件！");
        }
        return json;
    }
}
