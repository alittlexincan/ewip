package com.hyt.server.service.impl.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.sys.*;
import com.hyt.server.mapper.sys.*;
import com.hyt.server.service.sys.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @Author: XincanJiang
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("userService")
public class UserServiceImpl extends AbstractService<User> implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IOrganizationMapper organizationMapper;

    @Autowired
    private IUserGroupMapper userGroupMapper;

    @Autowired
    private IChannelMapper channelMapper;

    @Autowired
    private IAreaMapper areaMapper;

    @Override
    public PageInfo<User> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<User> areaList = this.userMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public User selectById(String id){
        return this.userMapper.selectById(id);
    }


    @Override
    public int insertUser(Map<String, Object> map) {

        return this.userMapper.insertUser(map);
    }

    @Override
    public int insertUserJob(Map<String, Object> map) {

        return this.userMapper.insertUserJob(map);
    }

    @Override
    public int updateUser(Map<String, Object> map) {

        return this.userMapper.updateUser(map);
    }

    @Override
    public int deleteUserJobById(String id) {
        return this.userMapper.deleteUserJobById(id);
    }

    @Override
    public int deleteUserJobByIds(String id) {
        Map<String,Object> map=new HashMap<>();
        map.put("id",id);
        return this.userMapper.deleteUserJobByIds(map);
    }

    @Override
    public List<User> selectList(Map<String, Object> map) {
        return this.userMapper.selectList(map);
    }

    @Override
    public JSONObject userDetails(Map<String, Object> map) {
        JSONObject json=new JSONObject();
        List<User> list=this.userMapper.userDetails(map);
        json.put("list",list);
        return json;
    }

    /**
     * 下载模板
     * @param map
     * @return
     */
    @Override
    public JSONObject downModel(Map<String, Object> map) {
        JSONObject jsonAll=new JSONObject();
        List<Area> area=areaMapper.areaList(map);
        JSONArray channelArry=new JSONArray();
        List<Channel> channelList=channelMapper.selectByParam(map);
        for(int c=0;c<channelList.size();c++){
            JSONObject channelJson=new JSONObject();
            channelJson.put("name",channelList.get(c).getName());
            channelArry.add(channelJson);
        }
        JSONArray provinceArry=new JSONArray();
        JSONArray cityArrys=new JSONArray();
        JSONArray countyArrys=new JSONArray();


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
            }
        }

        if(cityArrys.size()>0){
            for(int i=0;i<cityArrys.size();i++){
                JSONObject cityJson = cityArrys.getJSONObject(i);
                JSONArray countyArry=new JSONArray();
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
                }
                cityJson.put("children",countyArry);
                System.out.println(cityJson);
            }
        }

        if(provinceArry.size()>0) {
            for (int i = 0; i < provinceArry.size(); i++) {
                JSONObject provinceJson = provinceArry.getJSONObject(i);
                JSONArray cityArry=new JSONArray();
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
                }
                provinceJson.put("children", cityArry);
                System.out.println(provinceJson);
            }
        }
        jsonAll.put("data",provinceArry);
        jsonAll.put("cityArrys",cityArrys);
        jsonAll.put("countyArrys",countyArrys);
        jsonAll.put("channelArry",channelArry);
        return jsonAll;
    }

    @Override
    public JSONObject importData(Map<String, Object> map,List<Map<String,Object>> list ) {

        JSONObject json=new JSONObject();
        List<Area> areaList=areaMapper.areaList(map);
        List<Channel> channelList=channelMapper.selectByParam(map);
        List<User> list1=new ArrayList<>();

        List<User> listNew=new ArrayList<>();
        List<User> NewList=new ArrayList<>();
        int dd=0;
        if(list.size()>0){
            String areName="";
            for(Map<String,Object> m:list){
                String uuidUser = UUID.randomUUID().toString().replace("-", "");
                String uuidJob = UUID.randomUUID().toString().replace("-", "");
                User user = new User();
                String county= m.get("county").toString();
                String city= m.get("city").toString();
                String province= m.get("province").toString();
                String channel= m.get("channel").toString();
                String type= m.get("type").toString();
                String name= m.get("name").toString();
                String code= m.get("code").toString();
                String job= m.get("job").toString();
                String duties= m.get("duties").toString();
                String leader= m.get("leader").toString();
                String age= m.get("age").toString();
                String sex= m.get("sex").toString();
                String address= m.get("address").toString();
                String longitude= m.get("longitude").toString();
                String latitude= m.get("latitude").toString();
                if(sex=="女"){
                    sex="0";
                }else{
                    sex="1";
                }
                if(age=="" ){
                    age="50";
                }
                Integer typeId=2;
                String groupName="";
                //类型转换
                if(type.equals("责任人")){
                    typeId=1;
                    groupName="责任人群组";
                }else if(type.equals("信息员")){
                    typeId=2;
                    groupName="信息员群组";
                }
                //判断地区
                if(county!=null && !"".equals(county)){
                    areName=county;
                }else{
                    if(city!=null && !"".equals(city)){
                        areName=city;
                    }else{
                        areName=province;
                    }
                }
                String orgName="";
                orgName=areName+"用户单位";
                user.setId(uuidUser);
                user.setAreaId(areName);
                user.setChannelId(channel);
                user.setOrganizationId(orgName);
                user.setUserGroupId(groupName);
                user.setType(typeId);
                user.setName(name);
                user.setCode(code);
                user.setLongitude((Double.parseDouble(longitude)));
                user.setLatitude((Double.parseDouble(latitude)));
                user.setAltitude(0.0);
                user.setJob(job);
                user.setDuties(duties);
                user.setLeader(leader);
                user.setAge(age);
                user.setSex(sex);
                user.setAddress(address);
                user.setJobId(uuidJob);
                list1.add(user);
            }

            //根据渠道名称对比换成渠道ID
            for(User user: list1){
                for(Channel channel:channelList){
                    if(user.getChannelId().equals(channel.getName())){
                        user.setChannelId(channel.getId());
                    }
                }
            }

            //根据地区名称对比换成地区ID
            for(User user: list1){
                for(Area area:areaList){
                    if(user.getAreaId().equals(area.getAreaName())){
                        user.setAreaId(area.getId());
                    }
                }
            }
            //根据地区ID、机构名称对比换成机构ID
            List<Organization> orgList=organizationMapper.selectOrg(map);
            for (User user: list1){
                for(Organization organization:orgList){
                    if(user.getAreaId().equals(organization.getAreaId()) &&
                            user.getOrganizationId().equals(organization.getOrganizationName())){
                        user.setOrganizationId(organization.getId());
                        listNew.add(user);
                    }
                }
            }
            if(listNew.size()>0){
                //根据地区ID、机构ID、群组名称对比换成群组ID
                List<UserGroup> groupList=userGroupMapper.selectGroup(map);
                for (User user: listNew){
                    for(UserGroup userGroup:groupList){
                        if(user.getAreaId().equals(userGroup.getAreaId())
                                && user.getOrganizationId().equals(userGroup.getOrganizationId())
                                && user.getUserGroupId().equals(userGroup.getName())){
                            user.setUserGroupId(userGroup.getId());
                            NewList.add(user);
                        }
                    }
                }
            }
            List<User> userlist=userMapper.selectUser(map);
            List<User> userEndlist=new ArrayList<>();
            for(User users:userlist){
                for(User user:NewList){
                    if(users.getAreaId().equals(user.getAreaId())
                            && users.getOrganizationId().equals(user.getOrganizationId())
                            && users.getUserGroupId().equals(user.getUserGroupId())
                            && users.getChannelId().equals(user.getChannelId())
                            && users.getName().equals(user.getName())){
                        userEndlist.add(user);
                    }
                }
            }
            NewList.removeAll(userEndlist);
            System.out.println(NewList);
            if(NewList.size()>0){
                int a=this.userMapper.importUserData(NewList);
                int b=this.userMapper.importJobData(NewList);
                json.put("code","200");
                json.put("msg","导入成功！共导入数据"+NewList.size()+"条");
            }else{
                json.put("code","500");
                json.put("msg","导入失败！该文件数据已经导入");
            }
        }else{
            json.put("code","500");
            json.put("msg","导入失败请检查文件！");
        }
        return json;
    }


}
