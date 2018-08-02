package com.hyt.server.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.sys.Strategy;
import com.hyt.server.service.sys.IStrategyService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 策略配置管理控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Api(tags = {"策略配置管理"}, description = "StrategyController")
@RestController
@RequestMapping("/strategy")
public class StrategyController {

    @Autowired
    private IStrategyService strategyService;

    @ApiOperation(value="添加策略配置信息",httpMethod="POST",notes="根据参数列表添加策略配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="areaId",value="地区ID",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="organizationId",value="机构ID",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterId", value="灾种ID", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterName",value="灾种名称", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterColor",value="灾种颜色", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="disasterLevel",value="灾种级别", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="flow",value="策略流程", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="channelId",value="渠道配置", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        Strategy strategy = JSON.parseObject(json.toJSONString(), new TypeReference<Strategy>() {});
        int num = this.strategyService.insert(strategy);
        if(num>0){
            return ResultResponse.make(200,"添加策略配置成功",strategy);
        }
        return ResultResponse.make(500,"添加策略配置失败",null);
    }

    @ApiOperation(value="修改策略配置信息",httpMethod="POST", notes="根据策略配置ID，修改参数列表策略配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="策略配置ID", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name="areaId",value="地区ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="organizationId",value="机构ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterId", value="灾种ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterName",value="灾种名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterColor",value="灾种颜色", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="disasterLevel",value="灾种级别", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="flow",value="策略流程", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="channelId",value="渠道配置", dataType = "String", paramType = "query")
    })
    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        Strategy strategy = JSON.parseObject(json.toJSONString(), new TypeReference<Strategy>() {});
        int num = this.strategyService.update(strategy);
        if(num>0){
            return ResultResponse.make(200,"修改策略配置成功");
        }
        return ResultResponse.make(500,"修改策略配置失败");
    }

    @ApiOperation(value="删除策略配置信息",httpMethod = "DELETE", notes="根据url的策略配置ID来删除策略配置信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "策略配置ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.strategyService.deleteById(id);
        if(num>0){
          return  ResultResponse.make(200,"删除策略配置成功");
        }
        return ResultResponse.make(500,"删除策略配置失败");
    }

    @ApiOperation(value="批量删除策略配置信息",httpMethod = "POST", notes="根据一批策略配置ID来删除策略配置信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "策略配置ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.strategyService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除策略配置成功");
        }
        return ResultResponse.make(500,"删除策略配置失败");
    }

    @ApiOperation(value="查询策略配置信息",httpMethod = "POST", notes="根据url的策略配置ID来查询策略配置信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "策略配置ID", required = true, dataType = "String", paramType="path")
    })
    @PostMapping("/select/{id}")
    public ResultObject<Object> selectById(@PathVariable(value = "id") String id) {
        return ResultResponse.ok(this.strategyService.selectById(id));
    }

    @ApiOperation(value = "查询策略配置信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有策略配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="startTime",value="开始时间", dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name="endTime",value="结束时间", dataType = "Date",paramType = "query"),

            @ApiImplicitParam(name="id",value="策略配置ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="areaId",value="地区ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="organizationId",value="机构ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterId", value="灾种ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterName",value="灾种名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterColor",value="灾种颜色", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="disasterLevel",value="灾种级别", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="flow",value="策略流程", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="channelId",value="渠道配置", dataType = "String", paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<Strategy> pageInfo = this.strategyService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value = "多条件查询策略配置信息", httpMethod = "GET", notes = "根据多条件查询策略配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="策略配置ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="areaId",value="地区ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="organizationId",value="机构ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterId", value="灾种ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterName",value="灾种名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterColor",value="灾种颜色", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="disasterLevel",value="灾种级别", dataType = "Integer",paramType = "query")
    })
    @GetMapping("/config")
    public ResultObject<Object> selectConfig(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        try {
            return  ResultResponse.make(200,"查询策略配置成功", this.strategyService.selectConfig(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ResultResponse.make(500,"查询策略配置失败", null);
    }

}
