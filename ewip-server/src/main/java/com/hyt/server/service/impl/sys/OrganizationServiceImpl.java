package com.hyt.server.service.impl.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.message.MessageUser;
import com.hyt.server.entity.sys.Area;
import com.hyt.server.entity.sys.Organization;
import com.hyt.server.mapper.sys.IAreaMapper;
import com.hyt.server.mapper.sys.IOrganizationMapper;
import com.hyt.server.service.sys.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 机构管理业务实现层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("organizationService")
public class OrganizationServiceImpl extends AbstractService<Organization> implements IOrganizationService {

    @Autowired
    private IOrganizationMapper organizationMapper;

    @Autowired
    private IAreaMapper areaMapper;

    /**
     * 分页查询机构信息
     * @param map
     * @return
     */
    @Override
    public PageInfo<Organization> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<Organization> organizationList = this.organizationMapper.findAll(map);
        return new PageInfo<>(organizationList);
    }

    @Override
    public Organization selectById(String id){
        return this.organizationMapper.selectById(id);
    }

    /**
     * 查询机构信息
     * @param map
     * @return
     */
    @Override
    public JSONObject selectOrg(Map<String, Object> map) {
        JSONObject json=new JSONObject();
        List<Organization> list=organizationMapper.selectOrg(map);
        json.put("list",list);
        return json;
    }

    @Override
    public JSONObject downModel(Map<String, Object> map) {
        JSONObject jsonAll=new JSONObject();
        List<Area> area=areaMapper.areaList(map);
        JSONArray provinceArry=new JSONArray();
        JSONArray cityArrys=new JSONArray();
        JSONArray countyArrys=new JSONArray();
        JSONArray countryArrys=new JSONArray();
        JSONArray cityArry=new JSONArray();
        JSONArray countyArry=new JSONArray();
        JSONArray countryArry=new JSONArray();
        Map<String,String> mapNew=new HashMap<>();
        for(int i=0;i<area.size();i++){
            String id=area.get(i).getId();
            String name=area.get(i).getAreaName();
            String pId=area.get(i).getpId();
            Integer level=area.get(i).getLevel();
            if(level==1){
                JSONObject json =new JSONObject();
                json.put("id",id);
                json.put("name",name);
                json.put("pid",pId);
                json.put("level",level);
                provinceArry.add(json);
            }else if(level==2){
                JSONObject json =new JSONObject();
                json.put("id",id);
                json.put("name",name);
                json.put("pid",pId);
                json.put("level",level);
                cityArrys.add(json);
            }else if(level==3){
                JSONObject json =new JSONObject();
                json.put("id",id);
                json.put("name",name);
                json.put("pid",pId);
                json.put("level",level);
                countyArrys.add(json);
            }else if(level==4){
                JSONObject json =new JSONObject();
                json.put("id",id);
                json.put("name",name);
                json.put("pid",pId);
                json.put("level",level);
                countryArrys.add(json);
            }
        }

        if(countyArrys.size()>0){
            for(int i=0;i<countyArrys.size();i++){
                JSONObject countyJson = countyArrys.getJSONObject(i);
                for(int j=0;j<countryArrys.size();j++){
                    JSONObject countryJson = countryArrys.getJSONObject(j);
                    if(countyJson.get("id").equals(countryJson.get("pid"))){
                        JSONObject json =new JSONObject();
                        json.put("id",countryJson.get("id"));
                        json.put("name",countryJson.get("name"));
                        json.put("pid",countryJson.get("pid"));
                        json.put("level",countryJson.get("level"));
                        countryArry.add(json);
                    }
                    countyJson.put("children",countryArry);
                }
            }
        }

        if(cityArrys.size()>0){
            for(int i=0;i<cityArrys.size();i++){
                JSONObject cityJson = cityArrys.getJSONObject(i);
                for(int j=0;j<countyArrys.size();j++){
                    JSONObject countyJson = countyArrys.getJSONObject(j);
                    if(cityJson.get("id").equals(countyJson.get("pid"))){
                        JSONObject json =new JSONObject();
                        json.put("id",countyJson.get("id"));
                        json.put("name",countyJson.get("name"));
                        json.put("pid",countyJson.get("pid"));
                        json.put("level",countyJson.get("level"));
                        json.put("children",countyJson.get("children"));
                        countyArry.add(json);
                    }
                    cityJson.put("children",countyArry);
                }
            }
        }
        if(provinceArry.size()>0) {
            for (int i = 0; i < provinceArry.size(); i++) {
                JSONObject provinceJson = provinceArry.getJSONObject(i);
                for (int j = 0; j < cityArrys.size(); j++) {
                    JSONObject cityJson = cityArrys.getJSONObject(j);
                    if (provinceJson.get("id").equals(cityJson.get("pid"))) {
                        JSONObject json = new JSONObject();
                        json.put("id",cityJson.get("id"));
                        json.put("name",cityJson.get("name"));
                        json.put("pid",cityJson.get("pid"));
                        json.put("level",cityJson.get("level"));
                        json.put("children",cityJson.get("children"));
                        cityArry.add(json);
                    }
                    provinceJson.put("children", cityArry);
                }
            }
        }
        jsonAll.put("data",provinceArry);
        jsonAll.put("cityArrys",cityArrys);
        jsonAll.put("countyArrys",countyArrys);
        jsonAll.put("countryArrys",countryArrys);
        return jsonAll;
    }





    @Override
    public JSONObject importData(Map<String, Object> map,List<Map<String,Object>> list ) {
        JSONObject json=new JSONObject();
        List<Area> areaList=areaMapper.areaList(map);
        List<Organization> list1=new ArrayList<>();
        if(list.size()>0){
            for(Map<String,Object> m:list){
                Organization organization = new Organization();
                String country= m.get("country").toString();
                String county= m.get("county").toString();
                String city= m.get("city").toString();
                String province= m.get("province").toString();
                String type= m.get("type").toString();
                String name= m.get("name").toString();
                String code= m.get("code").toString();
                Integer typeId=1;
                //类型转换
                if(type.equals("发布中心")){
                    typeId=0;
                }else if(type.equals("应急办")){
                    typeId=2;
                }else{
                    typeId=1;
                }
                //判断地区
                if(country!=null && !"".equals(country) ) {
                    organization.setAreaId(country);
                }else{
                    if(county!=null && !"".equals(county)){
                        organization.setAreaId(county);
                    }else{
                        if(city!=null && !"".equals(city)){
                            organization.setAreaId(city);
                        }else{
                            organization.setAreaId(province);
                        }
                    }
                }
                organization.setType(typeId);
                organization.setOrganizationName(name);
                organization.setCode(code);
                if(!name.equals("")){
                    list1.add(organization);
                }
            }
            for(Organization organization: list1){
                for(Area area:areaList){
                    if(organization.getAreaId().equals(area.getAreaName())){
                        organization.setAreaId(area.getId());
                    }
                }
            }
            List<Organization> orglist=organizationMapper.selectOrg(map);
            List<Organization> orgEndlist=new ArrayList<>();
            for(Organization organization:orglist){
                for(Organization org:list1){
                    if(organization.getAreaId().equals(org.getAreaId())
                            && organization.getOrganizationName().equals(org.getOrganizationName())){
                        orgEndlist.add(org);
                    }
                }
            }
            list1.removeAll(orgEndlist);
            System.out.println(list1);
            if(list1.size()>0){
                int a=this.organizationMapper.importData(list1);
                json.put("code","200");
                json.put("msg","导入成功！共"+list1.size()+"条数据");
            }else {
                json.put("code","500");
                json.put("msg","导入失败，该文件数据已经导入！");
            }
        }else{
            json.put("code","500");
            json.put("msg","导入失败，请检查文件！");
        }
        return json;
    }

}
