package com.hyt.server.service.impl.info;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.info.BiInfo;
import com.hyt.server.entity.info.VfInfo;
import com.hyt.server.mapper.info.IBiInfoMapper;
import com.hyt.server.mapper.info.IVfInfoMapper;
import com.hyt.server.service.info.IBiInfoService;
import com.hyt.server.service.info.IVfInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("vfInfoService")
public  class VfInfoServiceImpl extends AbstractService<VfInfo> implements IVfInfoService {

    @Autowired
    private IVfInfoMapper vfInfoMapper;

    @Override
    public PageInfo<VfInfo> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<VfInfo> list = this.vfInfoMapper.findAll(map);
        return new PageInfo<>(list);
    }

    @Override
    public List<VfInfo> selectList(Map<String, Object> map){
        return this.vfInfoMapper.findAll(map);
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
        List<VfInfo> listNew=new ArrayList<>();
        try {
            for(Map<String, Object> mapNew: list){
                VfInfo vfInfo = null;
                vfInfo = mapToObject(mapNew,VfInfo.class);
                vfInfo.setReportUnitCode(reportUnitCode);
                System.out.println(vfInfo);
                listNew.add(vfInfo);
            }
            if(listNew.size()>0){
                int a=this.vfInfoMapper.importData(listNew);
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
