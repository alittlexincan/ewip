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

import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-历史灾情控制层
 * @Date: Created in 18:04 2018-11-1
 * @Modified By:
 */
@Api(tags = {"基础数据-历史灾情"}, description = "DisasterHistoryController")
@RestController
@RequestMapping("/disasterHistory")
public class DisasterHistoryController {

    @Autowired
    private IDisasterHistoryService disasterHistoryService;

    @ApiOperation(value="添加历史灾情信息",httpMethod="POST",notes="根据参数列表添加历史灾情信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="type",value="灾害类型",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="color",value="等级颜色",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="severity", value="灾害等级", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="level",value="规模等级", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="damage",value="危害程度", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="code",value="灾害编码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="灾害名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="start_time",value="开始时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="end_time",value="结束时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="scale", value="影响范围", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="lon",value="经度", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="lat",value="纬度", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="monitor_people",value="监测人员", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="monitor_organ",value="监管单位", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="monitor_organ",value="监测时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="contact",value="联系方式", required = true, dataType = "String",paramType = "query")

    })
    @PostMapping("/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        DisasterHistory disasterHistory = JSON.parseObject(json.toJSONString(), new TypeReference<DisasterHistory>() {});
        int num = this.disasterHistoryService.insert(disasterHistory);
        if(num>0){
            return ResultResponse.make(200,"添加历史灾情成功",disasterHistory);
        }
        return ResultResponse.make(500,"添加历史灾情失败",null);
    }

    @ApiOperation(value="修改历史灾情信息",httpMethod="POST", notes="根据历史灾情ID，修改参数列表历史灾情信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="历史灾情ID", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name="type",value="灾害类型",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="color",value="等级颜色",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="severity", value="灾害等级", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="level",value="规模等级", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="damage",value="危害程度", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="code",value="灾害编码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="灾害名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="start_time",value="开始时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="end_time",value="结束时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="scale", value="影响范围", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="lon",value="经度", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="lat",value="纬度", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="monitor_people",value="监测人员", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="monitor_organ",value="监管单位", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="monitor_organ",value="监测时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="contact",value="联系方式", required = true, dataType = "String",paramType = "query")
    })
    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        DisasterHistory disasterHistory = JSON.parseObject(json.toJSONString(), new TypeReference<DisasterHistory>() {});
        int num = this.disasterHistoryService.update(disasterHistory);
        if(num>0){
            return ResultResponse.make(200,"修改历史灾情成功");
        }
        return ResultResponse.make(500,"修改历史灾情失败");
    }

    @ApiOperation(value="删除历史灾情信息",httpMethod = "DELETE", notes="根据url的历史灾情ID来删除历史灾情信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "历史灾情ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.disasterHistoryService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除历史灾情成功");
        }
        return ResultResponse.make(500,"删除历史灾情失败");
    }

    @ApiOperation(value="批量删除历史灾情信息",httpMethod = "POST", notes="根据一批历史灾情ID来删除历史灾情信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "历史灾情ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.disasterHistoryService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除历史灾情成功");
        }
        return ResultResponse.make(500,"删除历史灾情失败");
    }

    @ApiOperation(value = "查询历史灾情信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有历史灾情信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="type",value="灾害类型",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="color",value="等级颜色",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="severity", value="灾害等级", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="level",value="规模等级", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="damage",value="危害程度", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="code",value="灾害编码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="灾害名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="start_time",value="开始时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="end_time",value="结束时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="scale", value="影响范围", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="lon",value="经度", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="lat",value="纬度", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="monitor_people",value="监测人员", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="monitor_organ",value="监管单位", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="monitor_organ",value="监测时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="contact",value="联系方式", required = true, dataType = "String",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<DisasterHistory> pageInfo = this.disasterHistoryService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

}
