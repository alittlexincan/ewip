package com.hyt.server.service.impl.info;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.info.HosInfo;
import com.hyt.server.mapper.info.IHosInfoMapper;
import com.hyt.server.service.info.IHosInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("hosInfoService")
public  class HosInfoServiceImpl extends AbstractService<HosInfo> implements IHosInfoService {

    @Autowired
    private IHosInfoMapper hosInfoMapper;

    @Override
    public PageInfo<HosInfo> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<HosInfo> areaList = this.hosInfoMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<HosInfo> selectList(Map<String, Object> map){
        return this.hosInfoMapper.findAll(map);
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
        List<HosInfo> listNew=new ArrayList<>();
        try {
            for(Map<String, Object> mapNew: list){
                HosInfo hosInfo = null;
                hosInfo = mapToObject(mapNew,HosInfo.class);
                hosInfo.setReportUnitCode(reportUnitCode);
                System.out.println(hosInfo);
                listNew.add(hosInfo);
            }
            if(listNew.size()>0){
                int a=this.hosInfoMapper.importData(listNew);
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
