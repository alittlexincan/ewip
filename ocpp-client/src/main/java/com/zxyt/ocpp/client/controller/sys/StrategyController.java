package com.zxyt.ocpp.client.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.zxyt.ocpp.client.config.common.result.ResultObject;
import com.zxyt.ocpp.client.config.common.result.ResultResponse;
import com.zxyt.ocpp.client.entity.sys.Strategy;
import com.zxyt.ocpp.client.service.sys.IStrategyService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/strategy")
public class StrategyController {

    @Autowired
    private IStrategyService strategyService;

    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<Strategy> pageInfo = this.strategyService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @PostMapping("/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        Strategy strategy = JSON.parseObject(json.toJSONString(), new TypeReference<Strategy>() {});
        int num = this.strategyService.insert(strategy);
        if(num>0){
            return ResultResponse.make(200,"添加成功",strategy);
        }
        return ResultResponse.make(500,"添加失败",null);
    }

    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.strategyService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }

    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.strategyService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }

    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        Strategy strategy = JSON.parseObject(json.toJSONString(), new TypeReference<Strategy>() {});
        int num = this.strategyService.update(strategy);
        if(num>0){
            return ResultResponse.make(200,"修改成功");
        }
        return ResultResponse.make(500,"修改失败");
    }

    @GetMapping("/config")
    public ResultObject<Object> selectConfig(@RequestParam Map<String,Object> map) {
        try {
            return  ResultResponse.make(200,"查询策略配置成功", this.strategyService.selectConfig(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ResultResponse.make(500,"查询策略配置失败", null);
    }
}
