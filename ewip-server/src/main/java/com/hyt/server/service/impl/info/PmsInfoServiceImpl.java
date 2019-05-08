package com.hyt.server.service.impl.info;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.info.BiInfo;
import com.hyt.server.entity.info.PmsInfo;
import com.hyt.server.mapper.info.IPmsInfoMapper;
import com.hyt.server.service.info.IPmsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("pmsInfoService")
public  class PmsInfoServiceImpl extends AbstractService<PmsInfo> implements IPmsInfoService {

    @Autowired
    private IPmsInfoMapper pmsInfoMapper;

    @Override
    public PageInfo<PmsInfo> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<PmsInfo> areaList = this.pmsInfoMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<PmsInfo> selectList(Map<String, Object> map){
        return this.pmsInfoMapper.findAll(map);
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
        List<PmsInfo> listNew=new ArrayList<>();
        try {
            for(Map<String, Object> mapNew: list){
                PmsInfo pmsInfo = null;
                pmsInfo = mapToObject(mapNew,PmsInfo.class);
                pmsInfo.setReportUnitCode(reportUnitCode);
                System.out.println(pmsInfo);
                listNew.add(pmsInfo);
            }
            if(listNew.size()>0){
                int a=this.pmsInfoMapper.importData(listNew);
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
