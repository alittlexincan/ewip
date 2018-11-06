package com.hyt.server.controller.base;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.DisasterType;
import com.hyt.server.service.base.IDisasterTypeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-灾害种类控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-灾害种类"}, description = "DisasterTypeController")
@RestController
@RequestMapping("/disasterType")
public class DisasterTypeController {

    @Autowired
    private IDisasterTypeService disasterTypeService;

    @ApiOperation(value="添加灾害种类信息",httpMethod="POST",notes="根据参数列表添加灾害种类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="type",value="灾害类型",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="color",value="等级颜色",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="severity", value="灾害等级", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="level",value="规模等级", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="damage",value="危害程度", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="code",value="灾害编码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="灾害名称", required = true, dataType = "String", paramType = "query")

    })
    @PostMapping("/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        DisasterType disasterType = JSON.parseObject(json.toJSONString(), new TypeReference<DisasterType>() {});
        int num = this.disasterTypeService.insert(disasterType);
        if(num>0){
            return ResultResponse.make(200,"添加灾害种类成功",disasterType);
        }
        return ResultResponse.make(500,"添加灾害种类失败",null);
    }

    @ApiOperation(value="修改历史灾情信息",httpMethod="POST", notes="根据灾害种类ID，修改参数列表灾害种类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="历史灾情ID", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name="type",value="灾害类型",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="color",value="等级颜色",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="severity", value="灾害等级", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="level",value="规模等级", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="damage",value="危害程度", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="code",value="灾害编码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="灾害名称", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        DisasterType disasterType = JSON.parseObject(json.toJSONString(), new TypeReference<DisasterType>() {});
        int num = this.disasterTypeService.update(disasterType);
        if(num>0){
            return ResultResponse.make(200,"修改灾害种类成功");
        }
        return ResultResponse.make(500,"修改灾害种类失败");
    }

    @ApiOperation(value="删除灾害种类信息",httpMethod = "DELETE", notes="根据url的灾害种类ID来删除灾害种类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "灾害种类ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.disasterTypeService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除灾害种类成功");
        }
        return ResultResponse.make(500,"删除灾害种类失败");
    }

    @ApiOperation(value="批量删除灾害种类信息",httpMethod = "POST", notes="根据一批灾害种类ID来删除灾害种类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "灾害种类ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.disasterTypeService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除灾害种类成功");
        }
        return ResultResponse.make(500,"删除灾害种类失败");
    }

    @ApiOperation(value = "查询灾害种类信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有灾害种类信息")
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
            @ApiImplicitParam(name="name",value="灾害名称", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<DisasterType> pageInfo = this.disasterTypeService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

}
