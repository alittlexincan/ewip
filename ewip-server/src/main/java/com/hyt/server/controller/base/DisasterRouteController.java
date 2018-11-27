package com.hyt.server.controller.base;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.DisasterRoute;
import com.hyt.server.entity.base.FacilityOffice;
import com.hyt.server.service.base.IDisasterRouteService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-灾害路径控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-灾害路径"}, description = "DisasterRouteController")
@RestController
@RequestMapping("/disasterRoute")
public class DisasterRouteController {

    @Autowired
    private IDisasterRouteService disasterRouteService;


    @PostMapping("/insert")
    public ResultObject<Object> insert(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        DisasterRoute disasterRoute = JSON.parseObject(json.toJSONString(), new TypeReference<DisasterRoute>() {});
        int num = this.disasterRouteService.insert(disasterRoute);
        if(num>0){
            return ResultResponse.make(200,"添加成功",disasterRoute);
        }
        return ResultResponse.make(500,"添加失败",null);
    }


    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        DisasterRoute disasterRoute = JSON.parseObject(json.toJSONString(), new TypeReference<DisasterRoute>() {});
        int num = this.disasterRouteService.update(disasterRoute);
        if(num>0){
            return ResultResponse.make(200,"修改成功");
        }
        return ResultResponse.make(500,"修改失败");
    }

    @ApiOperation(value="删除灾害路径信息",httpMethod = "DELETE", notes="根据url的灾害路径ID来删除灾害路径信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "灾害路径ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.disasterRouteService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除灾害路径成功");
        }
        return ResultResponse.make(500,"删除灾害路径失败");
    }

    @ApiOperation(value="批量删除灾害路径信息",httpMethod = "POST", notes="根据一批灾害路径ID来删除灾害路径信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "灾害路径ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.disasterRouteService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除灾害路径成功");
        }
        return ResultResponse.make(500,"删除灾害路径失败");
    }

    @ApiOperation(value = "查询灾害路径信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有灾害路径信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="type",value="灾害类型",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="color",value="等级颜色",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="level",value="危害程度", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="灾害名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="points",value="灾害点", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="path",value="影响路线", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="length",value="线路长度", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="road_time", value="乘车耗时", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="range", value="经纬度范围", required = true, dataType = "String",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<DisasterRoute> pageInfo = this.disasterRouteService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value = "查询灾害路径信息列表", httpMethod = "GET", notes = "查询所有灾害路径信息")
    @GetMapping("/list")
    public ResultObject<Object> selectList(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        List<DisasterRoute> list = this.disasterRouteService.selectList(map);
        if(list.size()>0){
            return ResultResponse.make(200,"查询灾害路径成功",list);
        }
        return ResultResponse.make(500,"查询灾害路径失败",null);
    }        

}
