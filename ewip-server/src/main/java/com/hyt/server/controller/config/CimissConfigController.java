package com.hyt.server.controller.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.config.CimissConfig;
import com.hyt.server.service.config.ICimissConfigService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName CimissConfigController
 * @Description 获取cimiss配置信息
 * @Author Xincan
 * @Version 1.0
 **/
@Api(tags = {"Cimiss配置信息"}, description = "CimissConfigController")
@RestController
@RequestMapping("/cimissConfig")
public class CimissConfigController {

    @Autowired
    ICimissConfigService cimissConfigService;

    @ApiOperation(value="获取Cimiss请求Url",httpMethod="GET",notes="根据区域ID获取Cimiss请求Url")
    @ApiImplicitParam(name="areaId",value="区域ID",required = true, dataType = "String",paramType = "query")
    @GetMapping("/getRequestUrl")
    public JSONObject getRequestUrl(@RequestParam("areaId") String areaId) {
        return cimissConfigService.getRequestUrl(areaId);
    }

    @ApiOperation(value="添加Cimiss信息",httpMethod="POST",notes="根据参数列表添加Cimiss信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="areaId",value="地区ID",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="stationId",value="国家站号",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="stationCode",value="地区编码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="url",value="ip地址", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="userId",value="用户名",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="userPwd",value="密码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="interfaceId",value="接口名称",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="elements",value="要素信息",required = true, dataType = "String",paramType = "query")
    })
    @PostMapping("/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        CimissConfig config = JSON.parseObject(json.toJSONString(), new TypeReference<CimissConfig>() {});
        int num = this.cimissConfigService.insert(config);
        if(num>0){
            return ResultResponse.make(200,"添加Cimiss配置成功",config);
        }
        return ResultResponse.make(500,"添加Cimiss配置失败",null);
    }

    @ApiOperation(value="修改Cimiss信息",httpMethod="POST", notes="根据ID，修改Cimiss信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="配置ID", dataType = "String", required = true,paramType = "query"),
            @ApiImplicitParam(name="areaId",value="地区ID",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="stationId",value="国家站号",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="stationCode",value="地区编码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="url",value="ip地址", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="userId",value="用户名",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="userPwd",value="密码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="interfaceId",value="接口名称",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="elements",value="要素信息",required = true, dataType = "String",paramType = "query")
    })
    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        CimissConfig config = JSON.parseObject(json.toJSONString(), new TypeReference<CimissConfig>() {});
        int num = this.cimissConfigService.update(config);
        if(num>0){
            return ResultResponse.make(200,"修改Cimiss配置成功");
        }
        return ResultResponse.make(500,"修改Cimiss配置失败");
    }

    @ApiOperation(value="删除Cimiss信息",httpMethod = "DELETE", notes="根据id来删除Cimiss详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.cimissConfigService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除Cimiss配置成功");
        }
        return ResultResponse.make(500,"删除Cimiss配置失败");
    }

    @ApiOperation(value="批量删除Cimiss信息",httpMethod = "POST", notes="根据一批id来删除Cimiss信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.cimissConfigService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除Cimiss配置成功");
        }
        return ResultResponse.make(500,"删除Cimiss配置失败");
    }

    @ApiOperation(value="查询Cimiss配置信息",httpMethod = "GET", notes="根据id来查询Cimiss配置详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType="path")
    })
    @GetMapping("/select/{id}")
    public ResultObject<Object> selectById(@PathVariable(value = "id") String id) {
        return ResultResponse.ok(this.cimissConfigService.selectById(id));
    }

    @ApiOperation(value = "查询Cimiss配置信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有Cimiss配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="startTime",value="开始时间", dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name="endTime",value="结束时间", dataType = "Date",paramType = "query"),

            @ApiImplicitParam(name="id",value="ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="areaId",value="地区ID",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="stationId",value="国家站号",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="stationCode",value="地区编码",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="url",value="ip地址",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="userId",value="用户名",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="userPwd",value="密码",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="interfaceId",value="接口名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="elements",value="要素信息",dataType = "String",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<CimissConfig> pageInfo = this.cimissConfigService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }
}
