package com.hyt.server.controller.base;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.service.base.IDisasterHistoryService;
import com.hyt.server.entity.base.DisasterHistory;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-历史灾情控制层
 * @Date: Created in 18:04 2018-11-1
 * @Modified By:
 */
@RestController
@RequestMapping("/disasterHistory")
public class DisasterHistoryController {

    @Autowired
    private IDisasterHistoryService disasterHistoryService;

    @PostMapping("/insert")
    public ResultObject<Object> insert( @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        DisasterHistory disasterHistory = JSON.parseObject(json.toJSONString(), new TypeReference<DisasterHistory>() {});
        int num = this.disasterHistoryService.insert(disasterHistory);
        if(num>0){
            return ResultResponse.make(200,"添加历史灾情成功",disasterHistory);
        }
        return ResultResponse.make(500,"添加历史灾情失败",null);
    }


    @PostMapping("/update")
    public ResultObject<Object> update( @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        DisasterHistory disasterHistory = JSON.parseObject(json.toJSONString(), new TypeReference<DisasterHistory>() {});
        int num = this.disasterHistoryService.update(disasterHistory);
        if(num>0){
            return ResultResponse.make(200,"修改历史灾情成功");
        }
        return ResultResponse.make(500,"修改历史灾情失败");
    }

    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.disasterHistoryService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除历史灾情成功");
        }
        return ResultResponse.make(500,"删除历史灾情失败");
    }

    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.disasterHistoryService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除历史灾情成功");
        }
        return ResultResponse.make(500,"删除历史灾情失败");
    }

    @GetMapping("/select")
    public ResultObject<Object> selectAll( @RequestParam Map<String,Object> map) {
        PageInfo<DisasterHistory> pageInfo = this.disasterHistoryService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }


    @GetMapping("/list")
    public ResultObject<Object> selectList( @RequestParam Map<String,Object> map) {
        List<DisasterHistory> list = this.disasterHistoryService.selectList(map);
        if(list.size()>0){
            return ResultResponse.make(200,"查询历史灾情成功",list);
        }
        return ResultResponse.make(500,"查询历史灾情失败",null);
    }
}
