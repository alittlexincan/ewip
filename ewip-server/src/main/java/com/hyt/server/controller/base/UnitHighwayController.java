package com.hyt.server.controller.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.UnitHighway;
import com.hyt.server.service.base.IUnitHighwayService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-高速公路控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-高速公路"}, description = "UnitHighwayController")
@RestController
@RequestMapping("/unitHighway")
public class UnitHighwayController {

    @Autowired
    private IUnitHighwayService unitHighwayService;


    @ApiOperation(value="删除高速公路信息",httpMethod = "DELETE", notes="根据url的高速公路ID来删除高速公路信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "高速公路ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.unitHighwayService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除高速公路成功");
        }
        return ResultResponse.make(500,"删除高速公路失败");
    }

    @ApiOperation(value="批量删除高速公路信息",httpMethod = "POST", notes="根据一批高速公路ID来删除高速公路信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "高速公路ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.unitHighwayService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除高速公路成功");
        }
        return ResultResponse.make(500,"删除高速公路失败");
    }

    @ApiOperation(value = "查询高速公路信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有高速公路信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="name",value="高速名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="code",value="高速代码",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="start",value="起点", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="end",value="终点", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="province",value="所属省份", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="length",value="全线长", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="unit",value="所属部门",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="principal", value="负责人", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="phone", value="联系电话", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="description",value="高速描述", required = true, dataType = "String",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<UnitHighway> pageInfo = this.unitHighwayService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value="查询高速公路信息列表",httpMethod="GET",notes="查询所有高速公路信息用于地图展示")
    @GetMapping("/selectList")
    public ResultObject<Object> selectList(){
        return ResultResponse.ok(this.unitHighwayService.selectAll());
    }
}
