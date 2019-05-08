package com.hyt.server.service.impl.info;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.info.BiInfo;
import com.hyt.server.entity.info.GeoInfo;
import com.hyt.server.mapper.info.IBiInfoMapper;
import com.hyt.server.mapper.info.IGeoInfoMapper;
import com.hyt.server.service.info.IBiInfoService;
import com.hyt.server.service.info.IGeoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("geoInfoService")
public  class GeoInfoServiceImpl extends AbstractService<GeoInfo> implements IGeoInfoService {

    @Autowired
    private IGeoInfoMapper geoInfoMapper;

    @Override
    public PageInfo<GeoInfo> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<GeoInfo> list = this.geoInfoMapper.findAll(map);
        return new PageInfo<>(list);
    }

    @Override
    public List<GeoInfo> selectList(Map<String, Object> map){
        return this.geoInfoMapper.findAll(map);
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
        List<GeoInfo> listNew=new ArrayList<>();
        try {
            for(Map<String, Object> mapNew: list){
                GeoInfo geoInfo = null;
                geoInfo = mapToObject(mapNew,GeoInfo.class);
                geoInfo.setReportUnitCode(reportUnitCode);
                System.out.println(geoInfo);
                listNew.add(geoInfo);
            }
            if(listNew.size()>0){
                int a=this.geoInfoMapper.importData(listNew);
                json.put("code","200");
                json.put("msg","导入成功！共"+listNew.size()+"条数据");
            }else {
                json.put("code","500");
                json.put("msg","导入失败，该文件数据已经导入！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public List<Map<String, Object>> selectDisasterList(Map<String, Object> map) {
        return  this.geoInfoMapper.selectDisasterList(map);
    }
}
