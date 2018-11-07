package com.hyt.server.controller.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.UnitBridge;
import com.hyt.server.service.base.IUnitBridgeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-桥梁控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-桥梁"}, description = "UnitBridgeController")
@RestController
@RequestMapping("/unitBridge")
public class UnitBridgeController {

    @Autowired
    private IUnitBridgeService unitBridgeService;


    @ApiOperation(value="删除桥梁信息",httpMethod = "DELETE", notes="根据url的桥梁ID来删除桥梁信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "桥梁ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.unitBridgeService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除桥梁成功");
        }
        return ResultResponse.make(500,"删除桥梁失败");
    }

    @ApiOperation(value="批量删除桥梁信息",httpMethod = "POST", notes="根据一批桥梁ID来删除桥梁信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "桥梁ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.unitBridgeService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除桥梁成功");
        }
        return ResultResponse.make(500,"删除桥梁失败");
    }

    @ApiOperation(value = "查询桥梁信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有桥梁信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="name",value="桥梁名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="district",value="区县",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="type",value="桥型", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="address",value="桥址", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="length",value="主跨长度", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="buildTime",value="成桥时间", required = true, dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name="unit",value="所属部门",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="principal", value="负责人", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="phone", value="联系电话", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="description",value="桥梁描述", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="lon", value="负责人", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="lat", value="联系电话", required = true, dataType = "Double",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<UnitBridge> pageInfo = this.unitBridgeService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value="查询桥梁信息列表",httpMethod="GET",notes="查询所有桥梁信息用于地图展示")
    @GetMapping("/selectList")
    public ResultObject<Object> selectList(){
        return ResultResponse.ok(this.unitBridgeService.selectAll());
    }
}
