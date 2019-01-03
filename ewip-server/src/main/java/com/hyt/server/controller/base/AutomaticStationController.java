package com.hyt.server.controller.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.AutomaticStation;
import com.hyt.server.service.base.IAutomaticStationService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @Author: lxv
 * @Description: 基础数据-区域站控制层
 * @Date: Created in 18:04 2018-12-24
 * @Modified By:
 */
@Api(tags = {"基础数据-区域站"}, description = "AutomaticStationController")
@RestController
@RequestMapping("/automaticStation")
public class AutomaticStationController {

    @Autowired
    private IAutomaticStationService automaticStationService;

    @PostMapping("/insert")
    public ResultObject<Object> insert(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        AutomaticStation automaticStation = JSON.parseObject(json.toJSONString(), new TypeReference<AutomaticStation>() {});
        int num = this.automaticStationService.insert(automaticStation);
        if(num>0){
            return ResultResponse.make(200,"添加成功",automaticStation);
        }
        return ResultResponse.make(500,"添加失败",null);
    }

    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        AutomaticStation automaticStation = JSON.parseObject(json.toJSONString(), new TypeReference<AutomaticStation>() {});
        int num = this.automaticStationService.update(automaticStation);
        if(num>0){
            return ResultResponse.make(200,"修改成功");
        }
        return ResultResponse.make(500,"修改失败");
    }

    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.automaticStationService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }

    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.automaticStationService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }

    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<AutomaticStation> pageInfo = this.automaticStationService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @GetMapping("/list")
    public ResultObject<Object> selectList(@RequestParam Map<String,Object> map){
        List<AutomaticStation> list = this.automaticStationService.selectList(map);
        if(list.size()>0){
            return ResultResponse.make(200,"查询成功",list);
        }
        return ResultResponse.make(500,"查询失败",null);
    }}
