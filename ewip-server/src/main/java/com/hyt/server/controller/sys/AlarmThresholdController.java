package com.hyt.server.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.sys.AlarmThreshold;
import com.hyt.server.service.sys.IAlarmThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alarmThreshold")
public class AlarmThresholdController {

    @Autowired
    private IAlarmThresholdService alarmThresholdService;

    @PostMapping("/insert")
    public ResultObject<Object> insert( @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        AlarmThreshold alarmThreshold = JSON.parseObject(json.toJSONString(), new TypeReference<AlarmThreshold>() {});
        int num = this.alarmThresholdService.insert(alarmThreshold);
        if(num>0){
            return ResultResponse.make(200,"添加成功",alarmThreshold);
        }
        return ResultResponse.make(500,"添加失败",null);
    }

    @PostMapping("/update")
    public ResultObject<Object> update( @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        AlarmThreshold alarmThreshold = JSON.parseObject(json.toJSONString(), new TypeReference<AlarmThreshold>() {});
        int num = this.alarmThresholdService.update(alarmThreshold);
        if(num>0){
            return ResultResponse.make(200,"修改成功");
        }
        return ResultResponse.make(500,"修改失败");
    }

    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.alarmThresholdService.deleteById(id);
        if(num>0){
          return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }

    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.alarmThresholdService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }

    @PostMapping("/select/{id}")
    public ResultObject<Object> selectById(@PathVariable(value = "id") String id) {
        return ResultResponse.ok(this.alarmThresholdService.selectById(id));
    }

    @GetMapping("/select")
    public ResultObject<Object> selectAll( @RequestParam Map<String,Object> map) {
        PageInfo<AlarmThreshold> pageInfo = this.alarmThresholdService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @GetMapping("/select/all")
    public ResultObject<Object> select(@RequestParam Map<String,Object> map) {
        List<AlarmThreshold> list = this.alarmThresholdService.selectAll();
        if(list.size()>0){
            return  ResultResponse.make(200,"查询成功", list);
        }
        return ResultResponse.make(500,"查询失败",null);
    }

}
