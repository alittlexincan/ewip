package com.hyt.server.service.impl.info;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.info.BiInfo;
import com.hyt.server.entity.info.MfrInfo;
import com.hyt.server.mapper.info.IBiInfoMapper;
import com.hyt.server.mapper.info.IMfrInfoMapper;
import com.hyt.server.service.info.IBiInfoService;
import com.hyt.server.service.info.IMfrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("mfrInfoService")
public  class MfrInfoServiceImpl extends AbstractService<MfrInfo> implements IMfrInfoService {

    @Autowired
    private IMfrInfoMapper mfrInfoMapper;

    @Override
    public PageInfo<MfrInfo> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<MfrInfo> list = this.mfrInfoMapper.findAll(map);
        return new PageInfo<>(list);
    }

    @Override
    public List<MfrInfo> selectList(Map<String, Object> map){
        return this.mfrInfoMapper.findAll(map);
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
        List<MfrInfo> listNew=new ArrayList<>();
        try {
            for(Map<String, Object> mapNew: list){
                MfrInfo mfrInfo = null;
                mfrInfo = mapToObject(mapNew,MfrInfo.class);
                mfrInfo.setReportUnitCode(reportUnitCode);
                System.out.println(mfrInfo);
                listNew.add(mfrInfo);
            }
            if(listNew.size()>0){
                int a=this.mfrInfoMapper.importData(listNew);
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
}
