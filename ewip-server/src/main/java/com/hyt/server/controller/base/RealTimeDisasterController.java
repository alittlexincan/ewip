package com.hyt.server.controller.base;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.RealTimeDisaster;
import com.hyt.server.service.base.IRealTimeDisasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lxv
 * @Description: 基础数据-实时灾情控制层
 * @Date: Created in 18:04 2018-11-1
 * @Modified By:
 */
@RestController
@RequestMapping("/realTimeDisaster")
public class RealTimeDisasterController {

    @Autowired
    private IRealTimeDisasterService realTimeDisasterService;

    @PostMapping("/insert")
    public ResultObject<Object> insert( @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        RealTimeDisaster realTimeDisaster = JSON.parseObject(json.toJSONString(), new TypeReference<RealTimeDisaster>() {});
        int num = this.realTimeDisasterService.insert(map);
        if(num>0){
            return ResultResponse.make(200,"添加实时灾情成功",realTimeDisaster);
        }
        return ResultResponse.make(500,"添加实时灾情失败",null);
    }


    @PostMapping("/update")
    public ResultObject<Object> update( @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        RealTimeDisaster realTimeDisaster = JSON.parseObject(json.toJSONString(), new TypeReference<RealTimeDisaster>() {});
        int num = this.realTimeDisasterService.update(realTimeDisaster);
        if(num>0){
            return ResultResponse.make(200,"修改实时灾情成功");
        }
        return ResultResponse.make(500,"修改实时灾情失败");
    }

    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.realTimeDisasterService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除实时灾情成功");
        }
        return ResultResponse.make(500,"删除实时灾情失败");
    }

    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.realTimeDisasterService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除实时灾情成功");
        }
        return ResultResponse.make(500,"删除实时灾情失败");
    }

    @GetMapping("/select")
    public ResultObject<Object> selectAll( @RequestParam Map<String,Object> map) {
        PageInfo<RealTimeDisaster> pageInfo = this.realTimeDisasterService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }


    @GetMapping("/list")
    public ResultObject<Object> selectList( @RequestParam Map<String,Object> map) {
        List<RealTimeDisaster> list = this.realTimeDisasterService.selectList(map);
        if(list.size()>0){
            return ResultResponse.make(200,"查询实时灾情成功",list);
        }
        return ResultResponse.make(500,"查询实时灾情失败",null);
    }


    @PostMapping("/selectFile")
    public JSONObject selectFile(@RequestParam Map<String, Object> map){
        try {
            return this.realTimeDisasterService.selectFile(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
