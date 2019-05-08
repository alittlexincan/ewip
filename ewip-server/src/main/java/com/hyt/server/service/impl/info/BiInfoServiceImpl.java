package com.hyt.server.service.impl.info;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.info.BiInfo;
import com.hyt.server.mapper.info.IBiInfoMapper;
import com.hyt.server.service.info.IBiInfoService;
import com.hyt.server.utils.info.BiExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("biInfoService")
public  class BiInfoServiceImpl extends AbstractService<BiInfo> implements IBiInfoService {

    @Autowired
    private IBiInfoMapper biInfoMapper;

    @Override
    public PageInfo<BiInfo> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<BiInfo> list = this.biInfoMapper.findAll(map);
        return new PageInfo<>(list);
    }

    @Override
    public List<BiInfo> selectList(Map<String, Object> map){
        return this.biInfoMapper.findAll(map);
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
        List<BiInfo> listNew=new ArrayList<>();
        try {
            for(Map<String, Object> mapNew: list){
                BiInfo biInfo = null;
                biInfo = mapToObject(mapNew,BiInfo.class);
                biInfo.setReportUnitCode(reportUnitCode);
                System.out.println(biInfo);
                listNew.add(biInfo);
            }
            if(listNew.size()>0){
                int a=this.biInfoMapper.importData(listNew);
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
