package com.hyt.server.service.impl.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.DisasterHistory;
import com.hyt.server.entity.base.RealTimeDisaster;
import com.hyt.server.entity.base.RealTimeDisasterFile;
import com.hyt.server.entity.warn.WarnEdit;
import com.hyt.server.entity.warn.WarnEditFile;
import com.hyt.server.mapper.base.IDisasterHistoryMapper;
import com.hyt.server.mapper.base.IRealTimeDisasterFileMapper;
import com.hyt.server.mapper.base.IRealTimeDisasterMapper;
import com.hyt.server.mapper.warn.IWarnEditFileMapper;
import com.hyt.server.service.base.IDisasterHistoryService;
import com.hyt.server.service.base.IRealTimeDisasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:29 2018-11-1
 * @Modified By:
 */
@Service("realTimeDisasterService")
public  class RealTimeDisasterServiceImpl extends AbstractService<RealTimeDisaster> implements IRealTimeDisasterService {

    @Autowired
    private IRealTimeDisasterMapper realTimeDisasterMapper;

    /**
     * 预警编辑 文件上传 数据接口层
     */
    @Autowired
    private IRealTimeDisasterFileMapper realDisasterFileMapper;

    @Override
    public PageInfo<RealTimeDisaster> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<RealTimeDisaster> areaList = this.realTimeDisasterMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<RealTimeDisaster> selectList(Map<String, Object> map){
        return this.realTimeDisasterMapper.findAll(map);
    }

    @Override
    public int insert(Map<String, Object> map) {
        // 存储回执信息
        JSONObject json = new JSONObject(map);
        RealTimeDisaster realTimeDisaster = addRealTimeDisaster(json);  // 1：添加预警编辑基本信息
        String realTimeDisasterId = realTimeDisaster.getId();           // 2：预警基础信息ID
        Integer num=addDisasterFile(json, realTimeDisasterId);          // 6：添加预警编辑上传文件信息
        return num;
    }

    /**
     * 5：添加预警编辑上传文件信息
     * @param json
     * @param realTimeDisasterId
     * @return
     */
    private int addDisasterFile(JSONObject json, String realTimeDisasterId){
        List<RealTimeDisasterFile> list = new ArrayList<>();
        JSONArray files = json.getJSONArray("files");
        if(files != null && files.size() > 0 ){
            for(int i = 0; i<files.size(); i++){
                JSONObject file = files.getJSONObject(i);
                RealTimeDisasterFile realTimeDisasterFile = new RealTimeDisasterFile();
                realTimeDisasterFile.setWarnEditId(realTimeDisasterId);
                realTimeDisasterFile.setName(file.getString("name"));
                realTimeDisasterFile.setSize(file.getString("size"));
                realTimeDisasterFile.setUrl(file.getString("url"));
                list.add(realTimeDisasterFile);
            }
            return this.realDisasterFileMapper.insertList(list);
        }
        return 0;
    }

    /**
     * 1：添加预警编辑基本信息
     * @param json          接收信息
     * @return
     */
    private RealTimeDisaster addRealTimeDisaster(JSONObject json){
        RealTimeDisaster realTimeDisaster = new RealTimeDisaster();
        realTimeDisaster.setReportArea(json.getString("reportArea"));
        realTimeDisaster.setLon(json.getString("lon"));
        realTimeDisaster.setLat(json.getString("lat"));
        realTimeDisaster.setReportAreaCode(json.getString("reportAreaCode"));
        realTimeDisaster.setHappenDisasterArea(json.getString("happenDisasterArea"));
        realTimeDisaster.setDamage(json.getString("damage"));
        realTimeDisaster.setDisasterName(json.getString("disasterName"));
        realTimeDisaster.setAccompanyDisaster(json.getString("accompanyDisaster"));
        realTimeDisaster.setStartTime(json.getTimestamp("startTime"));
        realTimeDisaster.setEndTime(json.getTimestamp("endTime"));
        realTimeDisaster.setMeteorologicalElements(json.getString("meteorologicalElements"));
        realTimeDisaster.setInfluence(json.getString("influence"));
        this.realTimeDisasterMapper.insertSelective(realTimeDisaster);
        return  realTimeDisaster;
    }

    @Override
    public JSONObject selectFile(Map<String, Object> map) {
        JSONObject obj=new JSONObject();
        List<RealTimeDisasterFile> list=this.realDisasterFileMapper.selectByWarnEditId(map);
        if(list.size()>0){
            obj.put("list",list);
        }else {
            obj=null;
        }
        return obj;
    }

}
