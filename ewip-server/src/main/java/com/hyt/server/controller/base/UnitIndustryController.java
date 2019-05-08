package com.hyt.server.controller.base;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.UnitIndustry;
import com.hyt.server.service.base.IUnitIndustryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/unitIndustry")
public class UnitIndustryController {

    @Autowired
    private IUnitIndustryService unitIndustryService;


    @PostMapping("/insert")
    public ResultObject<Object> insert(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        UnitIndustry unitIndustry = JSON.parseObject(json.toJSONString(), new TypeReference<UnitIndustry>() {});
        int num = this.unitIndustryService.insert(unitIndustry);
        if(num>0){
            return ResultResponse.make(200,"添加成功",unitIndustry);
        }
        return ResultResponse.make(500,"添加失败",null);
    }

    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        UnitIndustry unitIndustry = JSON.parseObject(json.toJSONString(), new TypeReference<UnitIndustry>() {});
        int num = this.unitIndustryService.update(unitIndustry);
        if(num>0){
            return ResultResponse.make(200,"修改成功");
        }
        return ResultResponse.make(500,"修改失败");
    }

    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.unitIndustryService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除农业园区成功");
        }
        return ResultResponse.make(500,"删除农业园区失败");
    }


    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.unitIndustryService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除农业园区成功");
        }
        return ResultResponse.make(500,"删除农业园区失败");
    }

    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<UnitIndustry> pageInfo = this.unitIndustryService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }


    @GetMapping("/list")
    public ResultObject<Object> selectList( @RequestParam Map<String,Object> map){
        List<UnitIndustry> list = this.unitIndustryService.selectList(map);
        if(list.size()>0){
            return ResultResponse.make(200,"查询成功",list);
        }
        return ResultResponse.make(500,"查询失败",null);
    }
}
